package com.gitaforceo.app.service

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.media.AudioFormat
import android.media.AudioRecord
import android.media.MediaPlayer
import android.media.MediaRecorder
import android.util.Base64
import androidx.core.content.ContextCompat
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.withContext
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody.Companion.toRequestBody
import org.json.JSONArray
import org.json.JSONObject
import java.io.File
import java.io.FileOutputStream
import java.util.concurrent.TimeUnit

/**
 * Voice Service integrating Google Cloud Speech-to-Text and Text-to-Speech
 * for a truly conversational Gita Advisor experience.
 *
 * Uses WaveNet Indian English voices for a warm, soothing tone.
 */
class VoiceService(private val context: Context) {

    private val prefs = context.applicationContext
        .getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)

    private val client = OkHttpClient.Builder()
        .connectTimeout(30, TimeUnit.SECONDS)
        .readTimeout(60, TimeUnit.SECONDS)
        .writeTimeout(30, TimeUnit.SECONDS)
        .build()

    // ---- State ----

    private val _voiceState = MutableStateFlow(VoiceState.IDLE)
    val voiceState: StateFlow<VoiceState> = _voiceState.asStateFlow()

    private val _spokenText = MutableStateFlow("")
    val spokenText: StateFlow<String> = _spokenText.asStateFlow()

    private val _responseText = MutableStateFlow("")
    val responseText: StateFlow<String> = _responseText.asStateFlow()

    private val _errorMessage = MutableStateFlow<String?>(null)
    val errorMessage: StateFlow<String?> = _errorMessage.asStateFlow()

    private val _amplitude = MutableStateFlow(0f)
    val amplitude: StateFlow<Float> = _amplitude.asStateFlow()

    private var audioRecord: AudioRecord? = null
    private var mediaPlayer: MediaPlayer? = null
    private var isRecording = false
    private var recordedBytes = mutableListOf<Byte>()

    // ---- API Key ----

    val hasGoogleApiKey: Boolean
        get() = getGoogleApiKey().isNotEmpty()

    fun setGoogleApiKey(key: String) {
        prefs.edit().putString(KEY_GOOGLE_API, key).apply()
    }

    fun getGoogleApiKey(): String {
        return prefs.getString(KEY_GOOGLE_API, "") ?: ""
    }

    // Voice selection
    var selectedVoice: VoiceOption = VoiceOption.MALE_WISE
        private set

    fun setVoice(voice: VoiceOption) {
        selectedVoice = voice
        prefs.edit().putString(KEY_VOICE, voice.name).apply()
    }

    init {
        val savedVoice = prefs.getString(KEY_VOICE, null)
        if (savedVoice != null) {
            selectedVoice = try {
                VoiceOption.valueOf(savedVoice)
            } catch (_: Exception) {
                VoiceOption.MALE_WISE
            }
        }
    }

    // ---- Recording ----

    fun hasMicPermission(): Boolean {
        return ContextCompat.checkSelfPermission(
            context, Manifest.permission.RECORD_AUDIO
        ) == PackageManager.PERMISSION_GRANTED
    }

    fun startRecording() {
        if (!hasMicPermission()) {
            _errorMessage.value = "Microphone permission required"
            return
        }

        val sampleRate = 16000
        val channelConfig = AudioFormat.CHANNEL_IN_MONO
        val audioFormat = AudioFormat.ENCODING_PCM_16BIT
        val bufferSize = AudioRecord.getMinBufferSize(sampleRate, channelConfig, audioFormat)

        try {
            audioRecord = AudioRecord(
                MediaRecorder.AudioSource.MIC,
                sampleRate,
                channelConfig,
                audioFormat,
                bufferSize * 2
            )

            if (audioRecord?.state != AudioRecord.STATE_INITIALIZED) {
                _errorMessage.value = "Could not initialize audio recorder"
                return
            }

            recordedBytes.clear()
            isRecording = true
            _voiceState.value = VoiceState.LISTENING
            _spokenText.value = ""
            _errorMessage.value = null

            audioRecord?.startRecording()

            // Read audio in a background thread
            Thread {
                val buffer = ShortArray(bufferSize)
                while (isRecording) {
                    val read = audioRecord?.read(buffer, 0, buffer.size) ?: 0
                    if (read > 0) {
                        // Convert shorts to bytes (little-endian)
                        for (i in 0 until read) {
                            val value = buffer[i]
                            recordedBytes.add((value.toInt() and 0xFF).toByte())
                            recordedBytes.add((value.toInt() shr 8 and 0xFF).toByte())
                        }

                        // Calculate amplitude for visual feedback
                        var sum = 0L
                        for (i in 0 until read) {
                            sum += buffer[i] * buffer[i]
                        }
                        val rms = Math.sqrt(sum.toDouble() / read).toFloat()
                        _amplitude.value = (rms / Short.MAX_VALUE).coerceIn(0f, 1f)
                    }
                }
            }.start()
        } catch (e: SecurityException) {
            _errorMessage.value = "Microphone permission denied"
        } catch (e: Exception) {
            _errorMessage.value = "Recording error: ${e.message}"
        }
    }

    fun stopRecording() {
        isRecording = false
        try {
            audioRecord?.stop()
            audioRecord?.release()
        } catch (_: Exception) { }
        audioRecord = null
        _amplitude.value = 0f
    }

    // ---- Google Cloud Speech-to-Text ----

    suspend fun transcribeAudio(): String? = withContext(Dispatchers.IO) {
        val apiKey = getGoogleApiKey()
        if (apiKey.isEmpty()) {
            _errorMessage.value = "Google API key not configured"
            return@withContext null
        }

        if (recordedBytes.isEmpty()) {
            _errorMessage.value = "No audio recorded"
            return@withContext null
        }

        _voiceState.value = VoiceState.PROCESSING

        try {
            val audioBytes = recordedBytes.toByteArray()
            val audioBase64 = Base64.encodeToString(audioBytes, Base64.NO_WRAP)

            val requestJson = JSONObject().apply {
                put("config", JSONObject().apply {
                    put("encoding", "LINEAR16")
                    put("sampleRateHertz", 16000)
                    put("languageCode", "en-IN")
                    put("alternativeLanguageCodes", JSONArray().apply {
                        put("hi-IN")
                        put("en-US")
                    })
                    put("enableAutomaticPunctuation", true)
                    put("model", "latest_long")
                })
                put("audio", JSONObject().apply {
                    put("content", audioBase64)
                })
            }

            val mediaType = "application/json".toMediaType()
            val request = Request.Builder()
                .url("$STT_URL?key=$apiKey")
                .post(requestJson.toString().toRequestBody(mediaType))
                .build()

            val response = client.newCall(request).execute()
            val body = response.body?.string()

            if (!response.isSuccessful) {
                _errorMessage.value = "Speech recognition failed: ${response.code}"
                return@withContext null
            }

            val json = JSONObject(body ?: "{}")
            val results = json.optJSONArray("results")
            if (results == null || results.length() == 0) {
                _errorMessage.value = "Could not understand speech. Please try again."
                return@withContext null
            }

            val transcript = results.getJSONObject(0)
                .getJSONArray("alternatives")
                .getJSONObject(0)
                .getString("transcript")

            _spokenText.value = transcript
            transcript
        } catch (e: Exception) {
            _errorMessage.value = "Transcription error: ${e.message}"
            null
        }
    }

    // ---- Google Cloud Text-to-Speech ----

    suspend fun speakText(text: String): Boolean = withContext(Dispatchers.IO) {
        val apiKey = getGoogleApiKey()
        if (apiKey.isEmpty()) {
            _errorMessage.value = "Google API key not configured"
            return@withContext false
        }

        _voiceState.value = VoiceState.SPEAKING
        _responseText.value = text

        try {
            // Clean text for speech (remove markdown)
            val cleanText = text
                .replace(Regex("\\*\\*(.+?)\\*\\*"), "$1")
                .replace(Regex("\\*(.+?)\\*"), "$1")
                .replace(Regex("---+"), "")
                .replace("#", "")
                .trim()

            val voice = selectedVoice
            val requestJson = JSONObject().apply {
                put("input", JSONObject().apply {
                    put("text", cleanText)
                })
                put("voice", JSONObject().apply {
                    put("languageCode", voice.languageCode)
                    put("name", voice.voiceName)
                    put("ssmlGender", voice.gender)
                })
                put("audioConfig", JSONObject().apply {
                    put("audioEncoding", "MP3")
                    put("speakingRate", voice.speakingRate)
                    put("pitch", voice.pitch)
                    put("volumeGainDb", 0.0)
                    put("effectsProfileId", JSONArray().apply {
                        put("handset-class-device")
                    })
                })
            }

            val mediaType = "application/json".toMediaType()
            val request = Request.Builder()
                .url("$TTS_URL?key=$apiKey")
                .post(requestJson.toString().toRequestBody(mediaType))
                .build()

            val response = client.newCall(request).execute()
            val body = response.body?.string()

            if (!response.isSuccessful) {
                _errorMessage.value = "Voice synthesis failed: ${response.code}"
                return@withContext false
            }

            val json = JSONObject(body ?: "{}")
            val audioContent = json.getString("audioContent")
            val audioBytes = Base64.decode(audioContent, Base64.DEFAULT)

            // Write to temp file and play
            val tempFile = File(context.cacheDir, "advisor_response.mp3")
            FileOutputStream(tempFile).use { it.write(audioBytes) }

            withContext(Dispatchers.Main) {
                playAudioFile(tempFile)
            }

            true
        } catch (e: Exception) {
            _errorMessage.value = "Speech synthesis error: ${e.message}"
            _voiceState.value = VoiceState.IDLE
            false
        }
    }

    private fun playAudioFile(file: File) {
        stopPlayback()
        mediaPlayer = MediaPlayer().apply {
            setDataSource(file.absolutePath)
            setOnCompletionListener {
                _voiceState.value = VoiceState.IDLE
                it.release()
                mediaPlayer = null
            }
            setOnErrorListener { mp, _, _ ->
                _voiceState.value = VoiceState.IDLE
                mp.release()
                mediaPlayer = null
                true
            }
            prepare()
            start()
        }
    }

    fun stopPlayback() {
        try {
            mediaPlayer?.stop()
            mediaPlayer?.release()
        } catch (_: Exception) { }
        mediaPlayer = null
    }

    fun stopAll() {
        stopRecording()
        stopPlayback()
        _voiceState.value = VoiceState.IDLE
    }

    fun shutdown() {
        stopAll()
    }

    // ---- Voice Options ----

    enum class VoiceOption(
        val displayName: String,
        val voiceName: String,
        val languageCode: String,
        val gender: String,
        val speakingRate: Double,
        val pitch: Double,
        val description: String
    ) {
        MALE_WISE(
            "Krishna (Male, Wise)",
            "en-IN-Wavenet-D",
            "en-IN",
            "MALE",
            0.88,
            -1.5,
            "Deep, authoritative voice — like a wise elder"
        ),
        FEMALE_SOOTHING(
            "Saraswati (Female, Soothing)",
            "en-IN-Wavenet-A",
            "en-IN",
            "FEMALE",
            0.85,
            -0.5,
            "Warm, nurturing voice — calm and reflective"
        ),
        MALE_CALM(
            "Arjuna (Male, Calm)",
            "en-IN-Wavenet-B",
            "en-IN",
            "MALE",
            0.90,
            -1.0,
            "Balanced, measured tone — a trusted advisor"
        ),
        FEMALE_WARM(
            "Draupadi (Female, Warm)",
            "en-IN-Wavenet-C",
            "en-IN",
            "FEMALE",
            0.87,
            0.0,
            "Gentle, encouraging voice — supportive and clear"
        )
    }

    enum class VoiceState {
        IDLE,
        LISTENING,
        PROCESSING,
        SPEAKING
    }

    companion object {
        private const val PREFS_NAME = "gita_voice"
        private const val KEY_GOOGLE_API = "googleApiKey"
        private const val KEY_VOICE = "selectedVoice"
        private const val STT_URL = "https://speech.googleapis.com/v1/speech:recognize"
        private const val TTS_URL = "https://texttospeech.googleapis.com/v1/text:synthesize"
    }
}
