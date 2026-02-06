package com.gitaforceo.app.service

import android.content.Context
import android.speech.tts.TextToSpeech
import android.speech.tts.UtteranceProgressListener
import com.gitaforceo.app.model.Verse
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import java.util.Locale
import java.util.UUID

class AudioService(context: Context) {

    private val _isPlaying = MutableStateFlow(false)
    val isPlaying: StateFlow<Boolean> = _isPlaying.asStateFlow()

    private val _volume = MutableStateFlow(0.8f)
    val volume: StateFlow<Float> = _volume.asStateFlow()

    private var tts: TextToSpeech? = null
    private var isTtsReady = false
    private var isPaused = false

    init {
        tts = TextToSpeech(context.applicationContext) { status ->
            if (status == TextToSpeech.SUCCESS) {
                isTtsReady = true
                // Default to English (India) locale for a soothing voice
                val result = tts?.setLanguage(Locale("en", "IN"))
                if (result == TextToSpeech.LANG_MISSING_DATA ||
                    result == TextToSpeech.LANG_NOT_SUPPORTED
                ) {
                    // Fall back to British English
                    tts?.setLanguage(Locale("en", "GB"))
                }
                // Set a calm, slightly lower pitch for a soothing delivery
                tts?.setPitch(0.95f)
                tts?.setSpeechRate(0.85f)
            }
        }

        tts?.setOnUtteranceProgressListener(object : UtteranceProgressListener() {
            override fun onStart(utteranceId: String?) {
                _isPlaying.value = true
                isPaused = false
            }

            override fun onDone(utteranceId: String?) {
                _isPlaying.value = false
                isPaused = false
            }

            @Deprecated("Deprecated in Java")
            override fun onError(utteranceId: String?) {
                _isPlaying.value = false
                isPaused = false
            }

            override fun onError(utteranceId: String?, errorCode: Int) {
                _isPlaying.value = false
                isPaused = false
            }
        })
    }

    /**
     * Speak a text passage using English (India) locale by default.
     * @param text The text to speak.
     * @param language BCP-47 language tag, defaults to "en-IN".
     */
    fun speakVerse(text: String, language: String = "en-IN") {
        if (!isTtsReady) return
        stop()

        val locale = Locale.forLanguageTag(language)
        val langResult = tts?.setLanguage(locale)
        if (langResult == TextToSpeech.LANG_MISSING_DATA ||
            langResult == TextToSpeech.LANG_NOT_SUPPORTED
        ) {
            tts?.setLanguage(Locale("en", "GB"))
        }

        tts?.setPitch(0.95f)
        tts?.setSpeechRate(0.85f)

        val params = android.os.Bundle()
        params.putFloat(TextToSpeech.Engine.KEY_PARAM_VOLUME, _volume.value)

        val utteranceId = UUID.randomUUID().toString()
        tts?.speak(text, TextToSpeech.QUEUE_FLUSH, params, utteranceId)
        _isPlaying.value = true
    }

    /**
     * Speak Sanskrit text using Hindi (India) locale for authentic pronunciation.
     * Uses a slower rate and lower pitch for a meditative feel.
     * @param text The Sanskrit/transliterated text to speak.
     */
    fun speakSanskrit(text: String) {
        if (!isTtsReady) return

        val locale = Locale("hi", "IN")
        val langResult = tts?.setLanguage(locale)
        if (langResult == TextToSpeech.LANG_MISSING_DATA ||
            langResult == TextToSpeech.LANG_NOT_SUPPORTED
        ) {
            tts?.setLanguage(Locale("en", "IN"))
        }

        tts?.setPitch(0.9f)
        tts?.setSpeechRate(0.7f)

        val params = android.os.Bundle()
        params.putFloat(TextToSpeech.Engine.KEY_PARAM_VOLUME, _volume.value)

        val utteranceId = UUID.randomUUID().toString()
        tts?.speak(text, TextToSpeech.QUEUE_ADD, params, utteranceId)
        _isPlaying.value = true
    }

    /**
     * Speak the full verse including transliteration, translation, commentary,
     * corporate wisdom, and family wisdom.
     */
    fun speakFullVerse(verse: Verse) {
        if (!isTtsReady) return
        stop()

        val fullText = buildString {
            appendLine("${verse.displayReference}.")
            appendLine("${verse.transliteration}.")
            appendLine()
            appendLine("Translation: ${verse.translation}")
            appendLine()
            appendLine("Commentary: ${verse.commentary}")
            appendLine()
            appendLine("Corporate Wisdom: ${verse.corporateWisdom}")
            appendLine()
            appendLine("Family Wisdom: ${verse.familyWisdom}")
        }

        speakVerse(fullText)
    }

    /**
     * Pause the current speech playback.
     */
    fun pause() {
        tts?.stop()
        _isPlaying.value = false
        isPaused = true
    }

    /**
     * Resume playback. Note: Android TTS does not natively support pause/resume,
     * so the caller should re-invoke speakVerse to restart from the beginning
     * if true resume is needed. This method updates the playing state.
     */
    fun resume() {
        // Android TTS does not support true pause/resume.
        // The caller should re-speak the text if needed.
        // This is provided for state consistency with the iOS counterpart.
        isPaused = false
    }

    /**
     * Stop all speech playback immediately.
     */
    fun stop() {
        tts?.stop()
        _isPlaying.value = false
        isPaused = false
    }

    /**
     * Toggle between playing and paused states.
     */
    fun togglePlayback() {
        if (_isPlaying.value) {
            pause()
        } else if (isPaused) {
            // Cannot truly resume on Android TTS; caller should re-speak
            isPaused = false
        }
    }

    /**
     * Set the speech volume, clamped between 0.0 and 1.0.
     */
    fun setVolume(newVolume: Float) {
        _volume.value = newVolume.coerceIn(0f, 1f)
    }

    /**
     * Release TTS resources. Call when the service is no longer needed.
     */
    fun shutdown() {
        tts?.stop()
        tts?.shutdown()
        tts = null
        isTtsReady = false
        _isPlaying.value = false
    }
}
