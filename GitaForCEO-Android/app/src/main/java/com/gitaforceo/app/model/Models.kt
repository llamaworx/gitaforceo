package com.gitaforceo.app.model

import androidx.compose.ui.graphics.Color
import com.gitaforceo.app.ui.theme.*

data class Chapter(
    val id: Int,
    val name: String,
    val sanskritName: String,
    val summary: String,
    val verseCount: Int,
    val ceoInsight: String,
    val verses: List<Verse>
) {
    val displayTitle: String get() = "Chapter $id: $name"
}

data class Verse(
    val id: String,
    val chapterId: Int,
    val verseNumber: Int,
    val sanskrit: String,
    val transliteration: String,
    val translation: String,
    val commentary: String,
    val corporateWisdom: String,
    val familyWisdom: String,
    val themes: List<WisdomTheme>
) {
    val displayReference: String get() = "BG $chapterId.$verseNumber"
}

enum class WisdomTheme(
    val displayName: String,
    val icon: String,
    val description: String
) {
    LEADERSHIP(
        "Leadership & Vision", "crown",
        "Krishna's teachings on visionary leadership and inspiring others to act with purpose."
    ),
    DECISION_MAKING(
        "Decision Making", "branch",
        "Wisdom for making tough decisions without attachment to outcomes."
    ),
    DUTY_DHARMA(
        "Duty & Dharma", "scale",
        "Understanding your responsibilities in the boardroom and beyond."
    ),
    STRESS_RESILIENCE(
        "Stress & Resilience", "heart",
        "Ancient techniques for maintaining equanimity under corporate pressure."
    ),
    TEAM_BUILDING(
        "Team Building", "people",
        "Insights on nurturing talent and building cohesive teams."
    ),
    ETHICS_INTEGRITY(
        "Ethics & Integrity", "shield",
        "The Gita's uncompromising stance on righteous conduct in business."
    ),
    CHANGE_MANAGEMENT(
        "Change Management", "refresh",
        "Embracing transformation as a constant force in life and business."
    ),
    FAMILY_BALANCE(
        "Family & Balance", "home",
        "Harmonising the demands of corporate life with family responsibilities."
    ),
    SELF_MASTERY(
        "Self-Mastery", "brain",
        "Disciplining the mind for peak performance and inner peace."
    ),
    STRATEGIC_THINKING(
        "Strategic Thinking", "chess",
        "Long-term vision and the art of seeing the bigger picture."
    ),
    CONFLICT_RESOLUTION(
        "Conflict Resolution", "handshake",
        "Navigating conflicts with wisdom, fairness, and composure."
    ),
    SUCCESSION(
        "Succession & Legacy", "arrow_up",
        "Building lasting legacies and preparing the next generation of leaders."
    );

    val color: Color
        get() = when (this) {
            LEADERSHIP -> Saffron
            DECISION_MAKING -> DeepBlue
            DUTY_DHARMA -> SacredGold
            STRESS_RESILIENCE -> LotusRose
            TEAM_BUILDING -> ForestGreen
            ETHICS_INTEGRITY -> DeepBlue
            CHANGE_MANAGEMENT -> WarmAmber
            FAMILY_BALANCE -> LotusRose
            SELF_MASTERY -> Saffron
            STRATEGIC_THINKING -> ForestGreen
            CONFLICT_RESOLUTION -> WarmAmber
            SUCCESSION -> SacredGold
        }
}

data class ChatMessage(
    val id: String = java.util.UUID.randomUUID().toString(),
    val role: Role,
    val content: String,
    val timestamp: Long = System.currentTimeMillis(),
    val referencedVerses: List<String> = emptyList(),
    val isStreaming: Boolean = false
) {
    enum class Role { USER, ADVISOR, SYSTEM }
}

data class Conversation(
    val id: String = java.util.UUID.randomUUID().toString(),
    var title: String = "New Conversation",
    val messages: MutableList<ChatMessage> = mutableListOf(),
    val createdAt: Long = System.currentTimeMillis(),
    var updatedAt: Long = System.currentTimeMillis(),
    val scenario: ScenarioTemplate? = null
) {
    val messageCount: Int get() = messages.count { it.role != ChatMessage.Role.SYSTEM }
}

data class ScenarioTemplate(
    val id: String,
    val title: String,
    val icon: String,
    val description: String,
    val prompt: String,
    val category: Category
) {
    enum class Category(val displayName: String) {
        BOARDROOM("Boardroom"),
        LEADERSHIP("Leadership"),
        PERSONAL("Personal"),
        CRISIS("Crisis")
    }
}
