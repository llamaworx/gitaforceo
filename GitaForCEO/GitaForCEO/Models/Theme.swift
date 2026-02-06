import SwiftUI

enum WisdomTheme: String, CaseIterable, Identifiable, Hashable {
    case leadership = "Leadership & Vision"
    case decisionMaking = "Decision Making"
    case dutyDharma = "Duty & Dharma"
    case stressResilience = "Stress & Resilience"
    case teamBuilding = "Team Building"
    case ethicsIntegrity = "Ethics & Integrity"
    case changeManagement = "Change Management"
    case familyBalance = "Family & Balance"
    case selfMastery = "Self-Mastery"
    case strategicThinking = "Strategic Thinking"
    case conflictResolution = "Conflict Resolution"
    case succession = "Succession & Legacy"

    var id: String { rawValue }

    var icon: String {
        switch self {
        case .leadership: return "crown.fill"
        case .decisionMaking: return "arrow.triangle.branch"
        case .dutyDharma: return "scale.3d"
        case .stressResilience: return "heart.circle.fill"
        case .teamBuilding: return "person.3.fill"
        case .ethicsIntegrity: return "shield.checkered"
        case .changeManagement: return "arrow.triangle.2.circlepath"
        case .familyBalance: return "house.and.flag.fill"
        case .selfMastery: return "brain.head.profile"
        case .strategicThinking: return "chess.figure"
        case .conflictResolution: return "handshake.fill"
        case .succession: return "arrow.up.forward.circle.fill"
        }
    }

    var color: Color {
        switch self {
        case .leadership: return .saffron
        case .decisionMaking: return .deepBlue
        case .dutyDharma: return .sacredGold
        case .stressResilience: return .lotusRose
        case .teamBuilding: return .forestGreen
        case .ethicsIntegrity: return .deepBlue
        case .changeManagement: return .warmAmber
        case .familyBalance: return .lotusRose
        case .selfMastery: return .saffron
        case .strategicThinking: return .forestGreen
        case .conflictResolution: return .warmAmber
        case .succession: return .sacredGold
        }
    }

    var description: String {
        switch self {
        case .leadership:
            return "Krishna's teachings on visionary leadership and inspiring others to act with purpose."
        case .decisionMaking:
            return "Wisdom for making tough decisions without attachment to outcomes."
        case .dutyDharma:
            return "Understanding your responsibilities in the boardroom and beyond."
        case .stressResilience:
            return "Ancient techniques for maintaining equanimity under corporate pressure."
        case .teamBuilding:
            return "Insights on nurturing talent and building cohesive teams."
        case .ethicsIntegrity:
            return "The Gita's uncompromising stance on righteous conduct in business."
        case .changeManagement:
            return "Embracing transformation as a constant force in life and business."
        case .familyBalance:
            return "Harmonising the demands of corporate life with family responsibilities."
        case .selfMastery:
            return "Disciplining the mind for peak performance and inner peace."
        case .strategicThinking:
            return "Long-term vision and the art of seeing the bigger picture."
        case .conflictResolution:
            return "Navigating conflicts with wisdom, fairness, and composure."
        case .succession:
            return "Building lasting legacies and preparing the next generation of leaders."
        }
    }
}
