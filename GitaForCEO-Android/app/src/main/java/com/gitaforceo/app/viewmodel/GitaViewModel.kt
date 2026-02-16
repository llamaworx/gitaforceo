package com.gitaforceo.app.viewmodel

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import com.gitaforceo.app.data.GitaData
import com.gitaforceo.app.model.*
import com.gitaforceo.app.service.AudioService
import com.gitaforceo.app.service.BookmarkService
import com.gitaforceo.app.service.ConversationService
import com.gitaforceo.app.service.VoiceService
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import java.util.Calendar

class GitaViewModel(application: Application) : AndroidViewModel(application) {
    val chapters = GitaData.chapters
    val bookmarkService = BookmarkService(application)
    val audioService = AudioService(application)
    val conversationService = ConversationService(application)
    val voiceService = VoiceService(application)

    private val _searchText = MutableStateFlow("")
    val searchText: StateFlow<String> = _searchText.asStateFlow()

    private val _searchResults = MutableStateFlow<List<Verse>>(emptyList())
    val searchResults: StateFlow<List<Verse>> = _searchResults.asStateFlow()

    private val _selectedTab = MutableStateFlow(AppTab.HOME)
    val selectedTab: StateFlow<AppTab> = _selectedTab.asStateFlow()

    private val _wisdomMode = MutableStateFlow(WisdomMode.CORPORATE)
    val wisdomMode: StateFlow<WisdomMode> = _wisdomMode.asStateFlow()

    private val _hasCompletedOnboarding = MutableStateFlow(
        application.getSharedPreferences("gita_prefs", Context.MODE_PRIVATE)
            .getBoolean("onboarding_complete", false)
    )
    val hasCompletedOnboarding: StateFlow<Boolean> = _hasCompletedOnboarding.asStateFlow()

    val dailyVerse: Verse get() = GitaData.dailyVerse

    val dailyQuote: String
        get() {
            val dayOfYear = Calendar.getInstance().get(Calendar.DAY_OF_YEAR)
            val index = (dayOfYear - 1) % GitaData.appQuotes.size
            return GitaData.appQuotes[index]
        }

    val greeting: String
        get() {
            val hour = Calendar.getInstance().get(Calendar.HOUR_OF_DAY)
            return when (hour) {
                in 5..11 -> "Good Morning"
                in 12..16 -> "Good Afternoon"
                in 17..20 -> "Good Evening"
                else -> "Namaste"
            }
        }

    val greetingSubtitle: String
        get() {
            val hour = Calendar.getInstance().get(Calendar.HOUR_OF_DAY)
            return when (hour) {
                in 5..11 -> "Begin your day with ancient wisdom"
                in 12..16 -> "A moment of reflection amidst the day"
                in 17..20 -> "Unwind with timeless teachings"
                else -> "Let the Gita guide your thoughts"
            }
        }

    fun setTab(tab: AppTab) {
        _selectedTab.value = tab
    }

    fun updateSearch(query: String) {
        _searchText.value = query
        _searchResults.value = if (query.isBlank()) emptyList() else GitaData.searchVerses(query)
    }

    fun setWisdomMode(mode: WisdomMode) {
        _wisdomMode.value = mode
    }

    fun completeOnboarding() {
        _hasCompletedOnboarding.value = true
        getApplication<Application>()
            .getSharedPreferences("gita_prefs", Context.MODE_PRIVATE)
            .edit()
            .putBoolean("onboarding_complete", true)
            .apply()
    }

    fun versesForTheme(theme: WisdomTheme): List<Verse> = GitaData.versesForTheme(theme)

    override fun onCleared() {
        super.onCleared()
        audioService.shutdown()
        voiceService.shutdown()
    }
}

enum class AppTab(val label: String, val icon: String) {
    HOME("Home", "home"),
    ADVISOR("Advisor", "chat"),
    CHAPTERS("Chapters", "book"),
    THEMES("Themes", "grid"),
    SEARCH("Search", "search")
}

enum class WisdomMode(val displayName: String) {
    CORPORATE("Corporate"),
    FAMILY("Family"),
    BOTH("Both")
}
