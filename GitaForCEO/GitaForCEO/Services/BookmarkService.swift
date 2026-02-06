import Foundation

class BookmarkService: ObservableObject {
    @Published var bookmarkedVerseIds: Set<String> = []
    @Published var readVerseIds: Set<String> = []
    @Published var notes: [String: String] = [:]

    private let bookmarksKey = "bookmarkedVerses"
    private let readKey = "readVerses"
    private let notesKey = "verseNotes"
    private let defaults = UserDefaults.standard

    init() {
        loadBookmarks()
        loadReadVerses()
        loadNotes()
    }

    func isBookmarked(_ verseId: String) -> Bool {
        bookmarkedVerseIds.contains(verseId)
    }

    func toggleBookmark(_ verseId: String) {
        if bookmarkedVerseIds.contains(verseId) {
            bookmarkedVerseIds.remove(verseId)
        } else {
            bookmarkedVerseIds.insert(verseId)
        }
        saveBookmarks()
    }

    func markAsRead(_ verseId: String) {
        readVerseIds.insert(verseId)
        saveReadVerses()
    }

    func isRead(_ verseId: String) -> Bool {
        readVerseIds.contains(verseId)
    }

    func saveNote(for verseId: String, note: String) {
        if note.isEmpty {
            notes.removeValue(forKey: verseId)
        } else {
            notes[verseId] = note
        }
        saveNotes()
    }

    func getNote(for verseId: String) -> String {
        notes[verseId] ?? ""
    }

    var bookmarkedVerses: [Verse] {
        GitaData.allVerses.filter { bookmarkedVerseIds.contains($0.id) }
    }

    var readCount: Int {
        readVerseIds.count
    }

    var totalVerses: Int {
        GitaData.allVerses.count
    }

    var progressPercentage: Double {
        guard totalVerses > 0 else { return 0 }
        return Double(readCount) / Double(totalVerses) * 100
    }

    // MARK: - Persistence

    private func loadBookmarks() {
        if let saved = defaults.array(forKey: bookmarksKey) as? [String] {
            bookmarkedVerseIds = Set(saved)
        }
    }

    private func saveBookmarks() {
        defaults.set(Array(bookmarkedVerseIds), forKey: bookmarksKey)
    }

    private func loadReadVerses() {
        if let saved = defaults.array(forKey: readKey) as? [String] {
            readVerseIds = Set(saved)
        }
    }

    private func saveReadVerses() {
        defaults.set(Array(readVerseIds), forKey: readKey)
    }

    private func loadNotes() {
        if let saved = defaults.dictionary(forKey: notesKey) as? [String: String] {
            notes = saved
        }
    }

    private func saveNotes() {
        defaults.set(notes, forKey: notesKey)
    }
}
