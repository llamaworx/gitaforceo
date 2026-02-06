package com.gitaforceo.app.service

import android.content.Context
import android.content.SharedPreferences
import com.gitaforceo.app.data.GitaData
import com.gitaforceo.app.model.Verse
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class BookmarkService(context: Context) {

    private val prefs: SharedPreferences =
        context.applicationContext.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)

    private val _bookmarkedVerseIds = MutableStateFlow<Set<String>>(emptySet())
    val bookmarkedVerseIds: StateFlow<Set<String>> = _bookmarkedVerseIds.asStateFlow()

    private val _readVerseIds = MutableStateFlow<Set<String>>(emptySet())
    val readVerseIds: StateFlow<Set<String>> = _readVerseIds.asStateFlow()

    private val _notes = MutableStateFlow<Map<String, String>>(emptyMap())
    val notes: StateFlow<Map<String, String>> = _notes.asStateFlow()

    init {
        loadBookmarks()
        loadReadVerses()
        loadNotes()
    }

    // ---- Bookmarks ----

    fun isBookmarked(verseId: String): Boolean {
        return _bookmarkedVerseIds.value.contains(verseId)
    }

    fun toggleBookmark(verseId: String) {
        val current = _bookmarkedVerseIds.value.toMutableSet()
        if (current.contains(verseId)) {
            current.remove(verseId)
        } else {
            current.add(verseId)
        }
        _bookmarkedVerseIds.value = current
        saveBookmarks()
    }

    val bookmarkedVerses: List<Verse>
        get() = GitaData.allVerses.filter { _bookmarkedVerseIds.value.contains(it.id) }

    // ---- Read Tracking ----

    fun markAsRead(verseId: String) {
        val current = _readVerseIds.value.toMutableSet()
        current.add(verseId)
        _readVerseIds.value = current
        saveReadVerses()
    }

    fun isRead(verseId: String): Boolean {
        return _readVerseIds.value.contains(verseId)
    }

    // ---- Notes ----

    fun saveNote(verseId: String, note: String) {
        val current = _notes.value.toMutableMap()
        if (note.isEmpty()) {
            current.remove(verseId)
        } else {
            current[verseId] = note
        }
        _notes.value = current
        saveNotes()
    }

    fun getNote(verseId: String): String {
        return _notes.value[verseId] ?: ""
    }

    val bookmarkedCount: Int
        get() = _bookmarkedVerseIds.value.size

    // ---- Progress ----

    val readCount: Int
        get() = _readVerseIds.value.size

    val totalVerses: Int
        get() = GitaData.allVerses.size

    val progressPercentage: Double
        get() {
            if (totalVerses == 0) return 0.0
            return readCount.toDouble() / totalVerses.toDouble() * 100.0
        }

    // ---- Persistence ----

    private fun loadBookmarks() {
        val saved = prefs.getStringSet(KEY_BOOKMARKS, emptySet()) ?: emptySet()
        _bookmarkedVerseIds.value = saved.toSet()
    }

    private fun saveBookmarks() {
        prefs.edit()
            .putStringSet(KEY_BOOKMARKS, _bookmarkedVerseIds.value)
            .apply()
    }

    private fun loadReadVerses() {
        val saved = prefs.getStringSet(KEY_READ, emptySet()) ?: emptySet()
        _readVerseIds.value = saved.toSet()
    }

    private fun saveReadVerses() {
        prefs.edit()
            .putStringSet(KEY_READ, _readVerseIds.value)
            .apply()
    }

    private fun loadNotes() {
        val notesJson = prefs.getString(KEY_NOTES, null)
        if (notesJson != null) {
            try {
                val gson = com.google.gson.Gson()
                val type = object : com.google.gson.reflect.TypeToken<Map<String, String>>() {}.type
                val loaded: Map<String, String> = gson.fromJson(notesJson, type)
                _notes.value = loaded
            } catch (_: Exception) {
                _notes.value = emptyMap()
            }
        }
    }

    private fun saveNotes() {
        val gson = com.google.gson.Gson()
        val json = gson.toJson(_notes.value)
        prefs.edit()
            .putString(KEY_NOTES, json)
            .apply()
    }

    companion object {
        private const val PREFS_NAME = "gita_bookmarks"
        private const val KEY_BOOKMARKS = "bookmarkedVerses"
        private const val KEY_READ = "readVerses"
        private const val KEY_NOTES = "verseNotes"
    }
}
