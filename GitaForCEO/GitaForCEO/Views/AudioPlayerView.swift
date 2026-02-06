import SwiftUI

struct AudioPlayerView: View {
    @EnvironmentObject var audioPlayerVM: AudioPlayerViewModel

    var body: some View {
        if let verse = audioPlayerVM.currentVerse {
            VStack(spacing: 0) {
                if audioPlayerVM.isExpanded {
                    expandedPlayer(verse: verse)
                } else {
                    miniPlayer(verse: verse)
                }
            }
            .transition(.move(edge: .bottom).combined(with: .opacity))
        }
    }

    // MARK: - Mini Player

    private func miniPlayer(verse: Verse) -> some View {
        HStack(spacing: 12) {
            // Animated indicator
            HStack(spacing: 2) {
                ForEach(0..<3, id: \.self) { i in
                    Capsule()
                        .fill(Color.saffron)
                        .frame(width: 3, height: audioPlayerVM.audioService.isPlaying ? CGFloat.random(in: 8...16) : 6)
                        .animation(
                            audioPlayerVM.audioService.isPlaying
                                ? .easeInOut(duration: 0.4).repeatForever(autoreverses: true).delay(Double(i) * 0.15)
                                : .default,
                            value: audioPlayerVM.audioService.isPlaying
                        )
                }
            }
            .frame(width: 20)

            VStack(alignment: .leading, spacing: 2) {
                Text(verse.displayReference)
                    .font(.caption)
                    .fontWeight(.semibold)
                    .foregroundStyle(.saffron)

                Text(verse.translation)
                    .font(.caption2)
                    .foregroundStyle(.secondary)
                    .lineLimit(1)
            }

            Spacer()

            Button {
                audioPlayerVM.togglePlayback()
            } label: {
                Image(systemName: audioPlayerVM.audioService.isPlaying ? "pause.circle.fill" : "play.circle.fill")
                    .font(.title2)
                    .foregroundStyle(.saffron)
            }

            Button {
                audioPlayerVM.stop()
            } label: {
                Image(systemName: "xmark.circle.fill")
                    .font(.title3)
                    .foregroundStyle(.secondary)
            }
        }
        .padding(.horizontal)
        .padding(.vertical, 10)
        .background(.ultraThinMaterial, in: RoundedRectangle(cornerRadius: 16))
        .shadow(color: .black.opacity(0.1), radius: 8, y: -2)
        .padding(.horizontal, 8)
        .padding(.bottom, 4)
        .onTapGesture {
            withAnimation(.spring(response: 0.3)) {
                audioPlayerVM.isExpanded = true
            }
        }
    }

    // MARK: - Expanded Player

    private func expandedPlayer(verse: Verse) -> some View {
        VStack(spacing: 20) {
            // Drag handle
            Capsule()
                .fill(Color.secondary.opacity(0.3))
                .frame(width: 36, height: 5)
                .padding(.top, 8)
                .onTapGesture {
                    withAnimation(.spring(response: 0.3)) {
                        audioPlayerVM.isExpanded = false
                    }
                }

            // Verse info
            VStack(spacing: 8) {
                Text(verse.displayReference)
                    .font(.caption)
                    .fontWeight(.semibold)
                    .foregroundStyle(.saffron)

                Text(verse.translation)
                    .font(.subheadline)
                    .multilineTextAlignment(.center)
                    .lineLimit(3)
                    .padding(.horizontal)
            }

            // Animated waveform
            HStack(spacing: 3) {
                ForEach(0..<12, id: \.self) { i in
                    Capsule()
                        .fill(Color.saffron.opacity(0.6))
                        .frame(
                            width: 4,
                            height: audioPlayerVM.audioService.isPlaying
                                ? CGFloat.random(in: 8...32)
                                : 8
                        )
                        .animation(
                            audioPlayerVM.audioService.isPlaying
                                ? .easeInOut(duration: 0.5).repeatForever(autoreverses: true).delay(Double(i) * 0.08)
                                : .default,
                            value: audioPlayerVM.audioService.isPlaying
                        )
                }
            }
            .frame(height: 36)

            // Controls
            HStack(spacing: 32) {
                // Reading mode picker
                Menu {
                    ForEach(AudioPlayerViewModel.ReadingMode.allCases, id: \.self) { mode in
                        Button {
                            audioPlayerVM.readingMode = mode
                        } label: {
                            HStack {
                                Text(mode.rawValue)
                                if audioPlayerVM.readingMode == mode {
                                    Image(systemName: "checkmark")
                                }
                            }
                        }
                    }
                } label: {
                    Image(systemName: "text.alignleft")
                        .font(.title3)
                        .foregroundStyle(.secondary)
                }

                // Play/Pause
                Button {
                    if audioPlayerVM.audioService.isPlaying {
                        audioPlayerVM.togglePlayback()
                    } else {
                        audioPlayerVM.playVerse(verse)
                    }
                } label: {
                    Image(systemName: audioPlayerVM.audioService.isPlaying ? "pause.circle.fill" : "play.circle.fill")
                        .font(.system(size: 56))
                        .foregroundStyle(.saffron)
                }

                // Stop
                Button {
                    audioPlayerVM.stop()
                } label: {
                    Image(systemName: "stop.circle")
                        .font(.title3)
                        .foregroundStyle(.secondary)
                }
            }

            // Volume slider
            HStack(spacing: 12) {
                Image(systemName: "speaker.fill")
                    .font(.caption)
                    .foregroundStyle(.secondary)

                Slider(value: Binding(
                    get: { audioPlayerVM.audioService.volume },
                    set: { audioPlayerVM.audioService.setVolume($0) }
                ), in: 0...1)
                .tint(.saffron)

                Image(systemName: "speaker.wave.3.fill")
                    .font(.caption)
                    .foregroundStyle(.secondary)
            }
            .padding(.horizontal, 32)

            // Options
            Toggle("Read Sanskrit transliteration first", isOn: $audioPlayerVM.readSanskritFirst)
                .font(.caption)
                .tint(.saffron)
                .padding(.horizontal, 24)

            Spacer()
        }
        .padding(.bottom, 20)
        .background(.ultraThinMaterial, in: RoundedRectangle(cornerRadius: 24))
        .shadow(color: .black.opacity(0.15), radius: 16, y: -4)
    }
}
