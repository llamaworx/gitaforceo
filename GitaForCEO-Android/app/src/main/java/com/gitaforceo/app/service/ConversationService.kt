package com.gitaforceo.app.service

import android.content.Context
import android.content.SharedPreferences
import com.gitaforceo.app.data.GitaData
import com.gitaforceo.app.model.ChatMessage
import com.gitaforceo.app.model.Conversation
import com.gitaforceo.app.model.ScenarioTemplate
import com.gitaforceo.app.model.Verse
import com.gitaforceo.app.model.WisdomTheme
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.withContext
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody.Companion.toRequestBody
import org.json.JSONArray
import org.json.JSONObject
import java.util.concurrent.TimeUnit

class ConversationService(context: Context) {

    private val prefs: SharedPreferences =
        context.applicationContext.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)

    private val gson = Gson()
    private val client = OkHttpClient.Builder()
        .connectTimeout(30, TimeUnit.SECONDS)
        .readTimeout(60, TimeUnit.SECONDS)
        .writeTimeout(30, TimeUnit.SECONDS)
        .build()

    private val _currentConversation = MutableStateFlow<Conversation?>(null)
    val currentConversation: StateFlow<Conversation?> = _currentConversation.asStateFlow()

    private val _conversations = MutableStateFlow<List<Conversation>>(emptyList())
    val conversations: StateFlow<List<Conversation>> = _conversations.asStateFlow()

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()

    private val _errorMessage = MutableStateFlow<String?>(null)
    val errorMessage: StateFlow<String?> = _errorMessage.asStateFlow()

    init {
        loadConversations()
    }

    // ---- API Key Management ----

    val hasAPIKey: Boolean
        get() {
            val key = prefs.getString(KEY_API_KEY, "") ?: ""
            return key.isNotEmpty()
        }

    fun setAPIKey(key: String) {
        prefs.edit().putString(KEY_API_KEY, key).apply()
    }

    fun getAPIKey(): String {
        return prefs.getString(KEY_API_KEY, "") ?: ""
    }

    // ---- Conversation Management ----

    fun startNewConversation(scenario: ScenarioTemplate? = null): Conversation {
        val title = scenario?.title ?: "New Conversation"
        val conversation = Conversation(
            title = title,
            scenario = scenario
        )

        // Add system message grounded in Gita wisdom
        val systemMessage = ChatMessage(
            role = ChatMessage.Role.SYSTEM,
            content = buildSystemPrompt()
        )
        conversation.messages.add(systemMessage)

        _currentConversation.value = conversation
        return conversation
    }

    fun setCurrentConversation(conversation: Conversation) {
        _currentConversation.value = conversation
    }

    /**
     * Send a user message and get the advisor's response.
     * Attempts Claude API first; falls back to offline wisdom engine on failure.
     */
    suspend fun sendMessage(text: String) {
        val conversation = _currentConversation.value ?: return

        // Add user message
        val userMessage = ChatMessage(
            role = ChatMessage.Role.USER,
            content = text
        )
        conversation.messages.add(userMessage)

        // Add streaming placeholder
        val placeholder = ChatMessage(
            role = ChatMessage.Role.ADVISOR,
            content = "",
            isStreaming = true
        )
        conversation.messages.add(placeholder)

        _currentConversation.value = conversation
        _isLoading.value = true
        _errorMessage.value = null

        try {
            val response = callClaudeAPI(conversation.messages)

            // Remove placeholder and add real response
            conversation.messages.removeAll { it.isStreaming }
            val referencedVerses = findReferencedVerses(response)
            val advisorMessage = ChatMessage(
                role = ChatMessage.Role.ADVISOR,
                content = response,
                referencedVerses = referencedVerses
            )
            conversation.messages.add(advisorMessage)
            conversation.updatedAt = System.currentTimeMillis()

            // Auto-title from first user message
            if (conversation.messages.count { it.role == ChatMessage.Role.USER } == 1) {
                conversation.title = generateTitle(text)
            }

            _currentConversation.value = conversation
            saveConversation(conversation)
            _isLoading.value = false
        } catch (e: Exception) {
            // Provide offline wisdom when API is unavailable
            conversation.messages.removeAll { it.isStreaming }
            val offlineResponse = generateOfflineResponse(text)
            val advisorMessage = ChatMessage(
                role = ChatMessage.Role.ADVISOR,
                content = offlineResponse.text,
                referencedVerses = offlineResponse.verseIds
            )
            conversation.messages.add(advisorMessage)
            conversation.updatedAt = System.currentTimeMillis()

            _currentConversation.value = conversation
            saveConversation(conversation)
            _isLoading.value = false
        }
    }

    fun deleteConversation(id: String) {
        val current = _conversations.value.toMutableList()
        current.removeAll { it.id == id }
        _conversations.value = current

        if (_currentConversation.value?.id == id) {
            _currentConversation.value = null
        }
        persistConversations()
    }

    // ---- Claude API ----

    private suspend fun callClaudeAPI(messages: List<ChatMessage>): String =
        withContext(Dispatchers.IO) {
            val apiKey = getAPIKey()
            if (apiKey.isEmpty()) {
                throw APIException.NoAPIKey
            }

            val systemPrompt = messages.firstOrNull { it.role == ChatMessage.Role.SYSTEM }?.content
                ?: buildSystemPrompt()

            // Build API messages array (exclude system and streaming messages)
            val apiMessages = JSONArray()
            messages
                .filter { it.role != ChatMessage.Role.SYSTEM && !it.isStreaming }
                .forEach { msg ->
                    val obj = JSONObject()
                    obj.put("role", if (msg.role == ChatMessage.Role.USER) "user" else "assistant")
                    obj.put("content", msg.content)
                    apiMessages.put(obj)
                }

            val body = JSONObject().apply {
                put("model", "claude-sonnet-4-5-20250929")
                put("max_tokens", 1500)
                put("system", systemPrompt)
                put("messages", apiMessages)
            }

            val mediaType = "application/json".toMediaType()
            val requestBody = body.toString().toRequestBody(mediaType)

            val request = Request.Builder()
                .url(API_URL)
                .post(requestBody)
                .addHeader("content-type", "application/json")
                .addHeader("x-api-key", apiKey)
                .addHeader("anthropic-version", "2023-06-01")
                .build()

            val response = client.newCall(request).execute()

            if (!response.isSuccessful) {
                throw APIException.ApiError("API returned status ${response.code}")
            }

            val responseBody = response.body?.string()
                ?: throw APIException.ParseError("Empty response body")

            val json = JSONObject(responseBody)
            val content = json.getJSONArray("content")
            if (content.length() == 0) {
                throw APIException.ParseError("No content in response")
            }

            val firstBlock = content.getJSONObject(0)
            firstBlock.getString("text")
        }

    sealed class APIException(message: String) : Exception(message) {
        object NoAPIKey : APIException("No API key configured")
        class ApiError(message: String) : APIException(message)
        class ParseError(message: String) : APIException(message)
    }

    // ---- Offline Wisdom Engine ----

    private fun generateOfflineResponse(query: String): OfflineResponse {
        val lowered = query.lowercase()
        val relevantVerses = findRelevantVerses(lowered)
        val topVerses = relevantVerses.take(3)

        if (topVerses.isEmpty()) {
            val dailyVerse = GitaData.dailyVerse
            val text = buildString {
                appendLine("Namaste. While I ponder your question deeply, let me share today's wisdom:")
                appendLine()
                appendLine("**${dailyVerse.displayReference}**")
                appendLine("*${dailyVerse.translation}*")
                appendLine()
                appendLine("**For the Boardroom:** ${dailyVerse.corporateWisdom}")
                appendLine()
                appendLine("**For the Home:** ${dailyVerse.familyWisdom}")
                appendLine()
                appendLine("This verse, while perhaps not directly addressing your specific situation, carries a universal truth that applies to all of life's challenges. Reflect on how its teaching might illuminate your path.")
                appendLine()
                append("*For personalised guidance that draws on all 700 verses, please add your Claude API key in Settings.*")
            }
            return OfflineResponse(text, listOf(dailyVerse.id))
        }

        val response = buildString {
            appendLine("Namaste. Your question resonates deeply with the teachings of the Gita. Let me share the wisdom that speaks to your situation:")
            appendLine()

            topVerses.forEachIndexed { index, verse ->
                val connector = when (index) {
                    0 -> "First"
                    1 -> "Furthermore"
                    else -> "Finally"
                }
                appendLine("**$connector, ${verse.displayReference}:**")
                appendLine("*\"${verse.translation}\"*")
                appendLine()
                appendLine(determineBestWisdom(verse, lowered))
                appendLine()
                appendLine("---")
                appendLine()
            }

            appendLine("Reflect on these teachings. The Gita reminds us that wisdom is not found in hasty action but in understanding the deeper truth of our situation.")
            appendLine()
            append("*For richer, conversational guidance, add your Claude API key in Settings.*")
        }

        return OfflineResponse(response, topVerses.map { it.id })
    }

    private fun findRelevantVerses(query: String): List<Verse> {
        val keywords: List<Pair<String, List<WisdomTheme>>> = listOf(
            "leadership" to listOf(WisdomTheme.LEADERSHIP),
            "lead" to listOf(WisdomTheme.LEADERSHIP),
            "ceo" to listOf(WisdomTheme.LEADERSHIP),
            "board" to listOf(WisdomTheme.LEADERSHIP, WisdomTheme.CONFLICT_RESOLUTION),
            "decision" to listOf(WisdomTheme.DECISION_MAKING),
            "choose" to listOf(WisdomTheme.DECISION_MAKING),
            "stuck" to listOf(WisdomTheme.DECISION_MAKING),
            "conflict" to listOf(WisdomTheme.CONFLICT_RESOLUTION),
            "fight" to listOf(WisdomTheme.CONFLICT_RESOLUTION),
            "disagree" to listOf(WisdomTheme.CONFLICT_RESOLUTION),
            "stress" to listOf(WisdomTheme.STRESS_RESILIENCE),
            "burnout" to listOf(WisdomTheme.STRESS_RESILIENCE),
            "pressure" to listOf(WisdomTheme.STRESS_RESILIENCE),
            "anxious" to listOf(WisdomTheme.STRESS_RESILIENCE),
            "worried" to listOf(WisdomTheme.STRESS_RESILIENCE),
            "team" to listOf(WisdomTheme.TEAM_BUILDING),
            "people" to listOf(WisdomTheme.TEAM_BUILDING),
            "hire" to listOf(WisdomTheme.TEAM_BUILDING),
            "fire" to listOf(WisdomTheme.TEAM_BUILDING, WisdomTheme.DUTY_DHARMA),
            "let go" to listOf(WisdomTheme.DUTY_DHARMA, WisdomTheme.FAMILY_BALANCE),
            "ethics" to listOf(WisdomTheme.ETHICS_INTEGRITY),
            "right" to listOf(WisdomTheme.ETHICS_INTEGRITY),
            "wrong" to listOf(WisdomTheme.ETHICS_INTEGRITY),
            "honest" to listOf(WisdomTheme.ETHICS_INTEGRITY),
            "change" to listOf(WisdomTheme.CHANGE_MANAGEMENT),
            "transform" to listOf(WisdomTheme.CHANGE_MANAGEMENT),
            "family" to listOf(WisdomTheme.FAMILY_BALANCE),
            "wife" to listOf(WisdomTheme.FAMILY_BALANCE),
            "husband" to listOf(WisdomTheme.FAMILY_BALANCE),
            "children" to listOf(WisdomTheme.FAMILY_BALANCE),
            "kids" to listOf(WisdomTheme.FAMILY_BALANCE),
            "home" to listOf(WisdomTheme.FAMILY_BALANCE),
            "balance" to listOf(WisdomTheme.FAMILY_BALANCE),
            "mind" to listOf(WisdomTheme.SELF_MASTERY),
            "meditation" to listOf(WisdomTheme.SELF_MASTERY),
            "focus" to listOf(WisdomTheme.SELF_MASTERY),
            "discipline" to listOf(WisdomTheme.SELF_MASTERY),
            "strategy" to listOf(WisdomTheme.STRATEGIC_THINKING),
            "vision" to listOf(WisdomTheme.STRATEGIC_THINKING),
            "long-term" to listOf(WisdomTheme.STRATEGIC_THINKING),
            "legacy" to listOf(WisdomTheme.SUCCESSION),
            "succession" to listOf(WisdomTheme.SUCCESSION),
            "retire" to listOf(WisdomTheme.SUCCESSION),
            "duty" to listOf(WisdomTheme.DUTY_DHARMA),
            "responsibility" to listOf(WisdomTheme.DUTY_DHARMA),
            "takeover" to listOf(WisdomTheme.STRATEGIC_THINKING, WisdomTheme.STRESS_RESILIENCE),
            "scandal" to listOf(WisdomTheme.ETHICS_INTEGRITY, WisdomTheme.STRESS_RESILIENCE),
            "betray" to listOf(WisdomTheme.CONFLICT_RESOLUTION, WisdomTheme.STRESS_RESILIENCE)
        )

        val matchedThemes = mutableListOf<WisdomTheme>()
        for ((keyword, themes) in keywords) {
            if (query.contains(keyword)) {
                matchedThemes.addAll(themes)
            }
        }

        if (matchedThemes.isEmpty()) {
            // Default to broad text search
            return GitaData.searchVerses(query)
        }

        // Score verses by how many matched themes they contain, highest first
        return GitaData.allVerses
            .map { verse ->
                val score = verse.themes.count { theme -> matchedThemes.contains(theme) }
                verse to score
            }
            .filter { it.second > 0 }
            .sortedByDescending { it.second }
            .map { it.first }
    }

    private fun determineBestWisdom(verse: Verse, query: String): String {
        val familyKeywords = listOf(
            "family", "wife", "husband", "children", "kids",
            "home", "balance", "spouse", "parent", "dinner"
        )
        val isFamilyQuery = familyKeywords.any { query.contains(it) }

        return if (isFamilyQuery) {
            "**For the Home:** ${verse.familyWisdom}"
        } else {
            "**For the Boardroom:** ${verse.corporateWisdom}"
        }
    }

    // ---- Helpers ----

    private fun buildSystemPrompt(): String {
        val sb = StringBuilder()
        sb.appendLine(
            """You are a wise philosophical advisor rooted in the Bhagavad Gita, counselling CEOs, board members, and corporate leaders. Your name is "Gita Advisor."

Your personality:
- Warm, wise, and compassionate — like a trusted mentor
- You speak with calm authority, drawing from the Gita's 700 verses
- You address the user respectfully, occasionally using "Namaste"
- You bridge ancient wisdom with modern corporate reality
- You understand boardroom dynamics, M&A, team management, quarterly pressures, and family life

Your approach:
1. ALWAYS ground your advice in specific Gita verses — cite them as "BG Chapter.Verse" (e.g., BG 2.47)
2. Explain the verse's meaning in the context of the user's specific situation
3. Offer BOTH corporate wisdom AND family/personal wisdom where relevant
4. Be practical — give actionable guidance, not just philosophy
5. Be empathetic — acknowledge the difficulty of the situation before offering wisdom
6. Keep responses focused — 2-3 key verses per response, not a lecture
7. Use markdown formatting: **bold** for verse references, *italics* for Sanskrit terms

Here are key verses you should draw from (but you know all 700):
"""
        )

        for (verse in GitaData.allVerses) {
            sb.appendLine()
            sb.appendLine("${verse.displayReference}: \"${verse.translation}\"")
            sb.appendLine("Corporate context: ${verse.corporateWisdom}")
            sb.appendLine("Family context: ${verse.familyWisdom}")
        }

        sb.appendLine()
        sb.appendLine(
            "Remember: You are not just reciting verses. You are a living guide who deeply understands both the Gita and the modern corporate world. Respond as Krishna would to a modern Arjuna in a boardroom."
        )

        return sb.toString()
    }

    private fun findReferencedVerses(text: String): List<String> {
        val found = mutableListOf<String>()
        for (verse in GitaData.allVerses) {
            if (text.contains(verse.displayReference) ||
                text.contains("${verse.chapterId}.${verse.verseNumber}")
            ) {
                found.add(verse.id)
            }
        }
        return found
    }

    private fun generateTitle(text: String): String {
        val words = text.split(" ").take(6).joinToString(" ")
        return if (words.length > 40) words.take(40) + "..." else words
    }

    // ---- Persistence ----

    private fun saveConversation(conversation: Conversation) {
        val current = _conversations.value.toMutableList()
        val index = current.indexOfFirst { it.id == conversation.id }
        if (index >= 0) {
            current[index] = conversation
        } else {
            current.add(0, conversation)
        }
        _conversations.value = current
        persistConversations()
    }

    private fun loadConversations() {
        val json = prefs.getString(KEY_CONVERSATIONS, null) ?: return
        try {
            val type = object : TypeToken<List<ConversationDto>>() {}.type
            val dtos: List<ConversationDto> = gson.fromJson(json, type)
            _conversations.value = dtos.map { it.toConversation() }
        } catch (_: Exception) {
            _conversations.value = emptyList()
        }
    }

    private fun persistConversations() {
        try {
            val dtos = _conversations.value.map { ConversationDto.from(it) }
            val json = gson.toJson(dtos)
            prefs.edit().putString(KEY_CONVERSATIONS, json).apply()
        } catch (_: Exception) {
            // Silently fail on serialization errors
        }
    }

    /**
     * DTO for Gson serialization of Conversation, since Conversation uses mutable lists
     * and enum types that benefit from explicit mapping.
     */
    private data class ConversationDto(
        val id: String,
        val title: String,
        val messages: List<ChatMessageDto>,
        val createdAt: Long,
        val updatedAt: Long,
        val scenarioId: String? = null,
        val scenarioTitle: String? = null
    ) {
        fun toConversation(): Conversation {
            val conversation = Conversation(
                id = id,
                title = title,
                createdAt = createdAt,
                updatedAt = updatedAt
            )
            conversation.messages.addAll(messages.map { it.toChatMessage() })
            return conversation
        }

        companion object {
            fun from(conversation: Conversation): ConversationDto {
                return ConversationDto(
                    id = conversation.id,
                    title = conversation.title,
                    messages = conversation.messages.map { ChatMessageDto.from(it) },
                    createdAt = conversation.createdAt,
                    updatedAt = conversation.updatedAt,
                    scenarioId = conversation.scenario?.id,
                    scenarioTitle = conversation.scenario?.title
                )
            }
        }
    }

    private data class ChatMessageDto(
        val id: String,
        val role: String,
        val content: String,
        val timestamp: Long,
        val referencedVerses: List<String>,
        val isStreaming: Boolean
    ) {
        fun toChatMessage(): ChatMessage {
            val messageRole = when (role) {
                "USER" -> ChatMessage.Role.USER
                "ADVISOR" -> ChatMessage.Role.ADVISOR
                "SYSTEM" -> ChatMessage.Role.SYSTEM
                else -> ChatMessage.Role.USER
            }
            return ChatMessage(
                id = id,
                role = messageRole,
                content = content,
                timestamp = timestamp,
                referencedVerses = referencedVerses,
                isStreaming = isStreaming
            )
        }

        companion object {
            fun from(message: ChatMessage): ChatMessageDto {
                return ChatMessageDto(
                    id = message.id,
                    role = message.role.name,
                    content = message.content,
                    timestamp = message.timestamp,
                    referencedVerses = message.referencedVerses,
                    isStreaming = message.isStreaming
                )
            }
        }
    }

    private data class OfflineResponse(
        val text: String,
        val verseIds: List<String>
    )

    companion object {
        private const val PREFS_NAME = "gita_conversations"
        private const val KEY_CONVERSATIONS = "savedConversations"
        private const val KEY_API_KEY = "claudeAPIKey"
        private const val API_URL = "https://api.anthropic.com/v1/messages"
    }
}
