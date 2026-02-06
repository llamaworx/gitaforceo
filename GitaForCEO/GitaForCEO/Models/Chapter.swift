import Foundation

struct Chapter: Identifiable, Hashable {
    let id: Int
    let name: String
    let sanskritName: String
    let summary: String
    let verseCount: Int
    let ceoInsight: String
    let verses: [Verse]

    var displayTitle: String {
        "Chapter \(id): \(name)"
    }
}
