import Foundation

class AudioPlayerViewModel: ObservableObject {
    @Published var audioService: AudioService
    @Published var isExpanded = false
    @Published var currentVerse: Verse?
    @Published var autoPlayNext = false
    @Published var readSanskritFirst = false
    @Published var readingMode: ReadingMode = .full

    enum ReadingMode: String, CaseIterable {
        case translationOnly = "Translation Only"
        case withCommentary = "With Commentary"
        case full = "Full Reading"
        case corporateOnly = "Corporate Wisdom"
        case familyOnly = "Family Wisdom"
    }

    init(audioService: AudioService) {
        self.audioService = audioService
    }

    func playVerse(_ verse: Verse) {
        currentVerse = verse
        isExpanded = true

        var textToRead = ""

        if readSanskritFirst {
            textToRead += verse.transliteration + ".\n\n"
        }

        switch readingMode {
        case .translationOnly:
            textToRead += verse.translation
        case .withCommentary:
            textToRead += verse.translation + ".\n\n" + verse.commentary
        case .full:
            textToRead += verse.translation + ".\n\n"
            textToRead += "Commentary: " + verse.commentary + ".\n\n"
            textToRead += "Corporate Wisdom: " + verse.corporateWisdom + ".\n\n"
            textToRead += "Family Wisdom: " + verse.familyWisdom
        case .corporateOnly:
            textToRead += verse.translation + ".\n\n"
            textToRead += "Corporate Wisdom: " + verse.corporateWisdom
        case .familyOnly:
            textToRead += verse.translation + ".\n\n"
            textToRead += "Family Wisdom: " + verse.familyWisdom
        }

        audioService.speakVerse(textToRead)
    }

    func togglePlayback() {
        audioService.togglePlayback()
    }

    func stop() {
        audioService.stop()
        isExpanded = false
        currentVerse = nil
    }
}
