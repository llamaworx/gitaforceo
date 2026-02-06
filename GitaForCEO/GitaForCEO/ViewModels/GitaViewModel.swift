import Foundation
import SwiftUI

class GitaViewModel: ObservableObject {
    @Published var chapters: [Chapter] = GitaData.chapters
    @Published var selectedChapter: Chapter?
    @Published var selectedVerse: Verse?
    @Published var selectedTheme: WisdomTheme?
    @Published var searchText: String = ""
    @Published var wisdomMode: WisdomMode = .corporate

    enum WisdomMode: String, CaseIterable {
        case corporate = "Corporate"
        case family = "Family"
        case both = "Both"

        var icon: String {
            switch self {
            case .corporate: return "building.2.fill"
            case .family: return "house.and.flag.fill"
            case .both: return "circle.grid.2x2.fill"
            }
        }
    }

    var dailyVerse: Verse {
        GitaData.dailyVerse
    }

    var dailyQuote: String {
        let dayOfYear = Calendar.current.ordinality(of: .day, in: .year, for: Date()) ?? 1
        let index = (dayOfYear - 1) % GitaData.appQuotes.count
        return GitaData.appQuotes[index]
    }

    var searchResults: [Verse] {
        guard !searchText.isEmpty else { return [] }
        return GitaData.searchVerses(query: searchText)
    }

    func versesForTheme(_ theme: WisdomTheme) -> [Verse] {
        GitaData.versesForTheme(theme)
    }

    var greeting: String {
        let hour = Calendar.current.component(.hour, from: Date())
        switch hour {
        case 5..<12: return "Good Morning"
        case 12..<17: return "Good Afternoon"
        case 17..<21: return "Good Evening"
        default: return "Namaste"
        }
    }

    var greetingSubtitle: String {
        let hour = Calendar.current.component(.hour, from: Date())
        switch hour {
        case 5..<12: return "Begin your day with ancient wisdom"
        case 12..<17: return "A moment of reflection amidst the day"
        case 17..<21: return "Unwind with timeless teachings"
        default: return "Let the Gita guide your thoughts"
        }
    }
}
