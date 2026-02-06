import Foundation

struct ChatMessage: Identifiable, Codable, Equatable {
    let id: UUID
    let role: Role
    let content: String
    let timestamp: Date
    var referencedVerses: [String]
    var isStreaming: Bool

    enum Role: String, Codable {
        case user
        case advisor
        case system
    }

    init(
        id: UUID = UUID(),
        role: Role,
        content: String,
        timestamp: Date = Date(),
        referencedVerses: [String] = [],
        isStreaming: Bool = false
    ) {
        self.id = id
        self.role = role
        self.content = content
        self.timestamp = timestamp
        self.referencedVerses = referencedVerses
        self.isStreaming = isStreaming
    }

    static func userMessage(_ text: String) -> ChatMessage {
        ChatMessage(role: .user, content: text)
    }

    static func advisorMessage(_ text: String, verses: [String] = []) -> ChatMessage {
        ChatMessage(role: .advisor, content: text, referencedVerses: verses)
    }

    static func streamingPlaceholder() -> ChatMessage {
        ChatMessage(role: .advisor, content: "", isStreaming: true)
    }
}

struct Conversation: Identifiable, Codable {
    let id: UUID
    var title: String
    var messages: [ChatMessage]
    let createdAt: Date
    var updatedAt: Date
    var scenario: ScenarioTemplate?

    init(
        id: UUID = UUID(),
        title: String = "New Conversation",
        messages: [ChatMessage] = [],
        createdAt: Date = Date(),
        updatedAt: Date = Date(),
        scenario: ScenarioTemplate? = nil
    ) {
        self.id = id
        self.title = title
        self.messages = messages
        self.createdAt = createdAt
        self.updatedAt = updatedAt
        self.scenario = scenario
    }

    var lastMessage: String {
        messages.last?.content ?? ""
    }

    var messageCount: Int {
        messages.filter { $0.role != .system }.count
    }
}

struct ScenarioTemplate: Identifiable, Codable, Hashable {
    let id: String
    let title: String
    let icon: String
    let description: String
    let prompt: String
    let category: Category

    enum Category: String, Codable, CaseIterable, Hashable {
        case boardroom = "Boardroom"
        case leadership = "Leadership"
        case personal = "Personal"
        case crisis = "Crisis"
    }

    static let allScenarios: [ScenarioTemplate] = [
        // Boardroom
        ScenarioTemplate(
            id: "hostile-takeover",
            title: "Hostile Takeover",
            icon: "shield.lefthalf.filled",
            description: "Facing acquisition pressure or hostile bids",
            prompt: "I'm facing a hostile takeover attempt. The acquiring company is offering a premium but I believe our long-term vision is worth more. How does the Gita guide me through this?",
            category: .boardroom
        ),
        ScenarioTemplate(
            id: "board-conflict",
            title: "Board Conflict",
            icon: "person.2.slash",
            description: "Disagreements among board members",
            prompt: "There is a deep conflict on my board. Two factions have opposing views on the company's strategic direction, and I'm caught in the middle as CEO. What wisdom does the Gita offer?",
            category: .boardroom
        ),
        ScenarioTemplate(
            id: "ethical-dilemma",
            title: "Ethical Dilemma",
            icon: "scale.3d",
            description: "When profit conflicts with principles",
            prompt: "I've discovered that a profitable business practice in my company may be causing harm to a community. Stopping it will hurt our quarterly numbers significantly. What does the Gita teach about this?",
            category: .boardroom
        ),
        ScenarioTemplate(
            id: "succession",
            title: "Succession Planning",
            icon: "arrow.up.forward.circle",
            description: "Preparing for leadership transition",
            prompt: "I need to plan my succession as CEO. I have an internal candidate who is loyal but less talented, and an external candidate who is brilliant but unknown. How does the Gita approach this?",
            category: .boardroom
        ),

        // Leadership
        ScenarioTemplate(
            id: "letting-go",
            title: "Letting Go of People",
            icon: "person.badge.minus",
            description: "Making difficult personnel decisions",
            prompt: "I need to let go of a long-time executive who has been loyal but is now underperforming. They are also a personal friend. How does the Gita help me navigate this?",
            category: .leadership
        ),
        ScenarioTemplate(
            id: "vision-doubt",
            title: "Doubting Your Vision",
            icon: "eye.trianglebadge.exclamationmark",
            description: "When you question your own direction",
            prompt: "I've been leading this company for years but lately I'm questioning whether my vision is still right. The market is shifting and I feel uncertain. What would Krishna advise?",
            category: .leadership
        ),
        ScenarioTemplate(
            id: "team-motivation",
            title: "Motivating Your Team",
            icon: "flame.fill",
            description: "Inspiring people through tough times",
            prompt: "My team is demoralised after a failed product launch. Morale is at an all-time low. How can the Gita's teachings help me inspire them again?",
            category: .leadership
        ),
        ScenarioTemplate(
            id: "decision-paralysis",
            title: "Decision Paralysis",
            icon: "arrow.triangle.branch",
            description: "Stuck between critical choices",
            prompt: "I have a critical decision to make — expand into a new market or double down on our core. Both paths have merits and risks. I've been deliberating for weeks. What does the Gita say about making difficult choices?",
            category: .leadership
        ),

        // Personal
        ScenarioTemplate(
            id: "work-life",
            title: "Work-Life Balance",
            icon: "house.and.flag",
            description: "When work consumes your personal life",
            prompt: "My spouse says I'm never present even when I'm home. My children are growing up and I'm missing it. But the company demands everything. How does the Gita address this tension?",
            category: .personal
        ),
        ScenarioTemplate(
            id: "burnout",
            title: "Executive Burnout",
            icon: "battery.25percent",
            description: "Running on empty",
            prompt: "I'm exhausted. Despite external success, I feel empty inside. I dread Monday mornings and I've lost the passion I once had. What does the Gita teach about finding meaning when you're burned out?",
            category: .personal
        ),
        ScenarioTemplate(
            id: "family-business",
            title: "Family Business Tensions",
            icon: "figure.2.and.child.holdinghands",
            description: "When family and business intertwine",
            prompt: "I run a family business and my siblings want to take the company in a different direction. Family dinners have become boardroom battles. How does the Gita — which itself is set amidst a family conflict — guide me?",
            category: .personal
        ),
        ScenarioTemplate(
            id: "legacy",
            title: "Defining Your Legacy",
            icon: "star.circle",
            description: "What you'll leave behind",
            prompt: "I'm in the later stage of my career and thinking about legacy. What does the Gita teach about building something that outlasts you? What truly matters in the end?",
            category: .personal
        ),

        // Crisis
        ScenarioTemplate(
            id: "company-crisis",
            title: "Company in Crisis",
            icon: "exclamationmark.triangle",
            description: "Navigating existential threats",
            prompt: "My company is in serious trouble — revenue is plummeting, key people are leaving, and the media is hostile. I feel like Arjuna on the battlefield. How does the Gita help a leader face potential ruin?",
            category: .crisis
        ),
        ScenarioTemplate(
            id: "public-scandal",
            title: "Reputation Crisis",
            icon: "newspaper",
            description: "When public trust is broken",
            prompt: "A scandal has broken involving my company. Even though I wasn't directly responsible, I'm the face of the organisation. Trust is shattered. What does the Gita teach about restoring righteousness?",
            category: .crisis
        ),
        ScenarioTemplate(
            id: "betrayal",
            title: "Betrayal by a Trusted Ally",
            icon: "person.badge.shield.checkmark",
            description: "When someone you trusted lets you down",
            prompt: "My most trusted deputy has been working against me behind the scenes. I feel deeply betrayed. The Gita opens with a similar conflict — what wisdom does it offer for this kind of personal and professional betrayal?",
            category: .crisis
        )
    ]
}
