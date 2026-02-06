import Foundation
import AVFoundation

class AudioService: ObservableObject {
    @Published var isPlaying = false
    @Published var currentTime: TimeInterval = 0
    @Published var duration: TimeInterval = 0
    @Published var playbackRate: Float = 1.0
    @Published var volume: Float = 0.8

    private var speechSynthesizer = AVSpeechSynthesizer()
    private var speechDelegate: SpeechDelegate?
    private var currentUtterance: AVSpeechUtterance?

    init() {
        configureAudioSession()
        speechDelegate = SpeechDelegate(service: self)
        speechSynthesizer.delegate = speechDelegate
    }

    private func configureAudioSession() {
        do {
            let session = AVAudioSession.sharedInstance()
            try session.setCategory(.playback, mode: .spokenAudio, options: [.mixWithOthers])
            try session.setActive(true)
        } catch {
            print("Audio session configuration error: \(error.localizedDescription)")
        }
    }

    func speakVerse(_ text: String, language: String = "en-IN") {
        stop()

        let utterance = AVSpeechUtterance(string: text)
        utterance.voice = AVSpeechSynthesisVoice(language: language)
            ?? AVSpeechSynthesisVoice(language: "en-GB")
        utterance.rate = AVSpeechUtteranceDefaultSpeechRate * 0.85
        utterance.pitchMultiplier = 0.95
        utterance.volume = volume
        utterance.preUtteranceDelay = 0.3
        utterance.postUtteranceDelay = 0.2

        currentUtterance = utterance
        speechSynthesizer.speak(utterance)
        isPlaying = true
    }

    func speakSanskrit(_ text: String) {
        let utterance = AVSpeechUtterance(string: text)
        utterance.voice = AVSpeechSynthesisVoice(language: "hi-IN")
        utterance.rate = AVSpeechUtteranceDefaultSpeechRate * 0.7
        utterance.pitchMultiplier = 0.9
        utterance.volume = volume
        utterance.preUtteranceDelay = 0.5
        utterance.postUtteranceDelay = 0.5

        currentUtterance = utterance
        speechSynthesizer.speak(utterance)
        isPlaying = true
    }

    func speakFullVerse(_ verse: Verse) {
        stop()

        let fullText = """
        \(verse.displayReference).
        \(verse.transliteration).

        Translation: \(verse.translation)

        Commentary: \(verse.commentary)

        Corporate Wisdom: \(verse.corporateWisdom)

        Family Wisdom: \(verse.familyWisdom)
        """

        speakVerse(fullText)
    }

    func pause() {
        speechSynthesizer.pauseSpeaking(at: .immediate)
        isPlaying = false
    }

    func resume() {
        speechSynthesizer.continueSpeaking()
        isPlaying = true
    }

    func stop() {
        speechSynthesizer.stopSpeaking(at: .immediate)
        isPlaying = false
    }

    func togglePlayback() {
        if isPlaying {
            pause()
        } else if speechSynthesizer.isPaused {
            resume()
        }
    }

    func setVolume(_ newVolume: Float) {
        volume = max(0, min(1, newVolume))
    }
}

private class SpeechDelegate: NSObject, AVSpeechSynthesizerDelegate {
    weak var service: AudioService?

    init(service: AudioService) {
        self.service = service
    }

    func speechSynthesizer(_ synthesizer: AVSpeechSynthesizer, didFinish utterance: AVSpeechUtterance) {
        DispatchQueue.main.async {
            self.service?.isPlaying = false
        }
    }

    func speechSynthesizer(_ synthesizer: AVSpeechSynthesizer, didPause utterance: AVSpeechUtterance) {
        DispatchQueue.main.async {
            self.service?.isPlaying = false
        }
    }

    func speechSynthesizer(_ synthesizer: AVSpeechSynthesizer, didContinue utterance: AVSpeechUtterance) {
        DispatchQueue.main.async {
            self.service?.isPlaying = true
        }
    }
}
