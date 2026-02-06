import Foundation

struct Verse: Identifiable, Hashable {
    let id: String
    let chapterId: Int
    let verseNumber: Int
    let sanskrit: String
    let transliteration: String
    let translation: String
    let commentary: String
    let corporateWisdom: String
    let familyWisdom: String
    let themes: [WisdomTheme]

    var displayReference: String {
        "BG \(chapterId).\(verseNumber)"
    }
}
