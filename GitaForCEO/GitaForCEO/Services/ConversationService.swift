import Foundation

class ConversationService: ObservableObject {
    @Published var currentConversation: Conversation?
    @Published var conversations: [Conversation] = []
    @Published var isLoading = false
    @Published var errorMessage: String?

    private let apiKey: String
    private let conversationsKey = "savedConversations"
    private let apiKeyStorageKey = "claudeAPIKey"

    init() {
        self.apiKey = UserDefaults.standard.string(forKey: "claudeAPIKey") ?? ""
        loadConversations()
    }

    var hasAPIKey: Bool {
        !apiKey.isEmpty && apiKey != ""
    }

    func setAPIKey(_ key: String) {
        UserDefaults.standard.set(key, forKey: apiKeyStorageKey)
    }

    // MARK: - Conversation Management

    func startNewConversation(scenario: ScenarioTemplate? = nil) -> Conversation {
        let title = scenario?.title ?? "New Conversation"
        var conversation = Conversation(title: title, scenario: scenario)

        // System message that grounds the AI in Gita wisdom
        let systemMessage = ChatMessage(
            role: .system,
            content: buildSystemPrompt()
        )
        conversation.messages.append(systemMessage)

        currentConversation = conversation
        return conversation
    }

    func sendMessage(_ text: String) async {
        guard var conversation = currentConversation else { return }

        let userMessage = ChatMessage.userMessage(text)
        conversation.messages.append(userMessage)

        let placeholder = ChatMessage.streamingPlaceholder()
        conversation.messages.append(placeholder)

        await MainActor.run {
            currentConversation = conversation
            isLoading = true
            errorMessage = nil
        }

        do {
            let response = try await callClaudeAPI(messages: conversation.messages)

            await MainActor.run {
                // Remove placeholder and add real response
                conversation.messages.removeAll { $0.isStreaming }

                // Find referenced verses
                let referencedVerses = findReferencedVerses(in: response)
                let advisorMessage = ChatMessage.advisorMessage(response, verses: referencedVerses)
                conversation.messages.append(advisorMessage)
                conversation.updatedAt = Date()

                // Auto-title from first exchange
                if conversation.messages.filter({ $0.role == .user }).count == 1 {
                    conversation.title = generateTitle(from: text)
                }

                currentConversation = conversation
                saveConversation(conversation)
                isLoading = false
            }
        } catch {
            await MainActor.run {
                conversation.messages.removeAll { $0.isStreaming }

                // Provide offline wisdom when API is unavailable
                let offlineResponse = generateOfflineResponse(for: text)
                let advisorMessage = ChatMessage.advisorMessage(offlineResponse.text, verses: offlineResponse.verses)
                conversation.messages.append(advisorMessage)
                conversation.updatedAt = Date()

                currentConversation = conversation
                saveConversation(conversation)
                isLoading = false
            }
        }
    }

    func deleteConversation(_ id: UUID) {
        conversations.removeAll { $0.id == id }
        if currentConversation?.id == id {
            currentConversation = nil
        }
        persistConversations()
    }

    // MARK: - Claude API

    private func callClaudeAPI(messages: [ChatMessage]) async throws -> String {
        let apiKey = UserDefaults.standard.string(forKey: apiKeyStorageKey) ?? ""

        guard !apiKey.isEmpty else {
            throw APIError.noAPIKey
        }

        let url = URL(string: "https://api.anthropic.com/v1/messages")!
        var request = URLRequest(url: url)
        request.httpMethod = "POST"
        request.setValue("application/json", forHTTPHeaderField: "content-type")
        request.setValue(apiKey, forHTTPHeaderField: "x-api-key")
        request.setValue("2023-06-01", forHTTPHeaderField: "anthropic-version")

        // Convert messages to API format
        let systemPrompt = messages.first(where: { $0.role == .system })?.content ?? buildSystemPrompt()
        let apiMessages = messages
            .filter { $0.role != .system && !$0.isStreaming }
            .map { msg -> [String: String] in
                [
                    "role": msg.role == .user ? "user" : "assistant",
                    "content": msg.content
                ]
            }

        let body: [String: Any] = [
            "model": "claude-sonnet-4-5-20250929",
            "max_tokens": 1500,
            "system": systemPrompt,
            "messages": apiMessages
        ]

        request.httpBody = try JSONSerialization.data(withJSONObject: body)

        let (data, response) = try await URLSession.shared.data(for: request)

        guard let httpResponse = response as? HTTPURLResponse, httpResponse.statusCode == 200 else {
            throw APIError.apiError
        }

        guard let json = try JSONSerialization.jsonObject(with: data) as? [String: Any],
              let content = json["content"] as? [[String: Any]],
              let firstBlock = content.first,
              let text = firstBlock["text"] as? String else {
            throw APIError.parseError
        }

        return text
    }

    enum APIError: Error {
        case noAPIKey
        case apiError
        case parseError
    }

    // MARK: - Offline Wisdom Engine

    private func generateOfflineResponse(for query: String) -> (text: String, verses: [String]) {
        let lowered = query.lowercased()

        // Find relevant verses based on keywords
        let relevantVerses = findRelevantVerses(for: lowered)
        let topVerses = Array(relevantVerses.prefix(3))

        if topVerses.isEmpty {
            let dailyVerse = GitaData.dailyVerse
            return (
                text: """
                Namaste. While I ponder your question deeply, let me share today's wisdom:

                **\(dailyVerse.displayReference)**
                *\(dailyVerse.translation)*

                **For the Boardroom:** \(dailyVerse.corporateWisdom)

                **For the Home:** \(dailyVerse.familyWisdom)

                This verse, while perhaps not directly addressing your specific situation, carries a universal truth that applies to all of life's challenges. Reflect on how its teaching might illuminate your path.

                *For personalised guidance that draws on all 700 verses, please add your Claude API key in Settings.*
                """,
                verses: [dailyVerse.id]
            )
        }

        var response = "Namaste. Your question resonates deeply with the teachings of the Gita. Let me share the wisdom that speaks to your situation:\n\n"

        for (index, verse) in topVerses.enumerated() {
            let connector = index == 0 ? "First" : index == 1 ? "Furthermore" : "Finally"
            response += """
            **\(connector), \(verse.displayReference):**
            *"\(verse.translation)"*

            \(determineBestWisdom(verse: verse, query: lowered))

            ---

            """
        }

        response += "\nReflect on these teachings. The Gita reminds us that wisdom is not found in hasty action but in understanding the deeper truth of our situation.\n\n"
        response += "*For richer, conversational guidance, add your Claude API key in Settings.*"

        return (text: response, verses: topVerses.map { $0.id })
    }

    private func findRelevantVerses(for query: String) -> [Verse] {
        let keywords: [(String, [WisdomTheme])] = [
            ("leadership", [.leadership]),
            ("lead", [.leadership]),
            ("CEO", [.leadership]),
            ("board", [.leadership, .conflictResolution]),
            ("decision", [.decisionMaking]),
            ("choose", [.decisionMaking]),
            ("stuck", [.decisionMaking]),
            ("conflict", [.conflictResolution]),
            ("fight", [.conflictResolution]),
            ("disagree", [.conflictResolution]),
            ("stress", [.stressResilience]),
            ("burnout", [.stressResilience]),
            ("pressure", [.stressResilience]),
            ("anxious", [.stressResilience]),
            ("worried", [.stressResilience]),
            ("team", [.teamBuilding]),
            ("people", [.teamBuilding]),
            ("hire", [.teamBuilding]),
            ("fire", [.teamBuilding, .dutyDharma]),
            ("let go", [.dutyDharma, .familyBalance]),
            ("ethics", [.ethicsIntegrity]),
            ("right", [.ethicsIntegrity]),
            ("wrong", [.ethicsIntegrity]),
            ("honest", [.ethicsIntegrity]),
            ("change", [.changeManagement]),
            ("transform", [.changeManagement]),
            ("family", [.familyBalance]),
            ("wife", [.familyBalance]),
            ("husband", [.familyBalance]),
            ("children", [.familyBalance]),
            ("kids", [.familyBalance]),
            ("home", [.familyBalance]),
            ("balance", [.familyBalance]),
            ("mind", [.selfMastery]),
            ("meditation", [.selfMastery]),
            ("focus", [.selfMastery]),
            ("discipline", [.selfMastery]),
            ("strategy", [.strategicThinking]),
            ("vision", [.strategicThinking]),
            ("long-term", [.strategicThinking]),
            ("legacy", [.succession]),
            ("succession", [.succession]),
            ("retire", [.succession]),
            ("duty", [.dutyDharma]),
            ("responsibility", [.dutyDharma]),
            ("takeover", [.strategicThinking, .stressResilience]),
            ("scandal", [.ethicsIntegrity, .stressResilience]),
            ("betray", [.conflictResolution, .stressResilience]),
        ]

        var matchedThemes: [WisdomTheme] = []
        for (keyword, themes) in keywords {
            if query.contains(keyword) {
                matchedThemes.append(contentsOf: themes)
            }
        }

        if matchedThemes.isEmpty {
            // Default to broad search
            return GitaData.searchVerses(query: query)
        }

        // Collect verses matching the themes, prioritise verses matching multiple themes
        let allVerses = GitaData.allVerses
        let scored = allVerses.map { verse -> (Verse, Int) in
            let score = verse.themes.reduce(0) { acc, theme in
                acc + (matchedThemes.contains(theme) ? 1 : 0)
            }
            return (verse, score)
        }
        .filter { $0.1 > 0 }
        .sorted { $0.1 > $1.1 }

        return scored.map { $0.0 }
    }

    private func determineBestWisdom(verse: Verse, query: String) -> String {
        let familyKeywords = ["family", "wife", "husband", "children", "kids", "home", "balance", "spouse", "parent", "dinner"]
        let isFamilyQuery = familyKeywords.contains(where: { query.contains($0) })

        if isFamilyQuery {
            return "**For the Home:** \(verse.familyWisdom)"
        } else {
            return "**For the Boardroom:** \(verse.corporateWisdom)"
        }
    }

    // MARK: - Helpers

    private func buildSystemPrompt() -> String {
        var prompt = """
        You are a wise philosophical advisor rooted in the Bhagavad Gita, counselling CEOs, board members, and corporate leaders. Your name is "Gita Advisor."

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

        // Include all verses as reference
        for verse in GitaData.allVerses {
            prompt += """

            \(verse.displayReference): "\(verse.translation)"
            Corporate context: \(verse.corporateWisdom)
            Family context: \(verse.familyWisdom)

            """
        }

        prompt += """

        Remember: You are not just reciting verses. You are a living guide who deeply understands both the Gita and the modern corporate world. Respond as Krishna would to a modern Arjuna in a boardroom.
        """

        return prompt
    }

    private func findReferencedVerses(in text: String) -> [String] {
        var found: [String] = []
        for verse in GitaData.allVerses {
            if text.contains(verse.displayReference) || text.contains("\(verse.chapterId).\(verse.verseNumber)") {
                found.append(verse.id)
            }
        }
        return found
    }

    private func generateTitle(from text: String) -> String {
        let words = text.split(separator: " ").prefix(6).joined(separator: " ")
        return words.count > 40 ? String(words.prefix(40)) + "..." : words
    }

    // MARK: - Persistence

    private func saveConversation(_ conversation: Conversation) {
        if let index = conversations.firstIndex(where: { $0.id == conversation.id }) {
            conversations[index] = conversation
        } else {
            conversations.insert(conversation, at: 0)
        }
        persistConversations()
    }

    private func loadConversations() {
        guard let data = UserDefaults.standard.data(forKey: conversationsKey),
              let decoded = try? JSONDecoder().decode([Conversation].self, from: data) else {
            return
        }
        conversations = decoded
    }

    private func persistConversations() {
        guard let data = try? JSONEncoder().encode(conversations) else { return }
        UserDefaults.standard.set(data, forKey: conversationsKey)
    }
}
