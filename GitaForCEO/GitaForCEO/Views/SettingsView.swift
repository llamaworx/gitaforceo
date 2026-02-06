import SwiftUI

struct SettingsView: View {
    @EnvironmentObject var viewModel: GitaViewModel
    @EnvironmentObject var audioPlayerVM: AudioPlayerViewModel
    @EnvironmentObject var conversationService: ConversationService
    @AppStorage("hapticFeedback") private var hapticFeedback = true
    @AppStorage("autoPlayAudio") private var autoPlayAudio = false
    @AppStorage("dailyReminder") private var dailyReminder = true
    @AppStorage("readSanskritFirst") private var readSanskritFirst = false
    @AppStorage("claudeAPIKey") private var apiKey = ""
    @State private var showAPIKeyField = false

    var body: some View {
        List {
            // App Info
            Section {
                VStack(spacing: 8) {
                    Text("॥ गीता ॥")
                        .font(.system(size: 32, design: .serif))
                        .foregroundStyle(.saffron)

                    Text("Gita for CEOs")
                        .font(.title2)
                        .fontWeight(.semibold)

                    Text("Ancient Wisdom for Modern Leaders")
                        .font(.subheadline)
                        .foregroundStyle(.secondary)

                    Text("Version 1.0")
                        .font(.caption)
                        .foregroundStyle(.secondary)
                }
                .frame(maxWidth: .infinity)
                .padding(.vertical, 12)
            }

            // Wisdom Mode
            Section("Wisdom Focus") {
                Picker("Default View", selection: $viewModel.wisdomMode) {
                    ForEach(GitaViewModel.WisdomMode.allCases, id: \.self) { mode in
                        Label(mode.rawValue, systemImage: mode.icon)
                            .tag(mode)
                    }
                }
            }

            // Audio Settings
            Section("Audio & Voice") {
                Picker("Reading Mode", selection: $audioPlayerVM.readingMode) {
                    ForEach(AudioPlayerViewModel.ReadingMode.allCases, id: \.self) { mode in
                        Text(mode.rawValue).tag(mode)
                    }
                }

                Toggle("Read Sanskrit First", isOn: $readSanskritFirst)
                    .tint(.saffron)
                    .onChange(of: readSanskritFirst) { _, newValue in
                        audioPlayerVM.readSanskritFirst = newValue
                    }

                Toggle("Auto-play Audio", isOn: $autoPlayAudio)
                    .tint(.saffron)

                HStack {
                    Text("Volume")
                    Slider(
                        value: Binding(
                            get: { audioPlayerVM.audioService.volume },
                            set: { audioPlayerVM.audioService.setVolume($0) }
                        ),
                        in: 0...1
                    )
                    .tint(.saffron)
                }
            }

            // Notifications
            Section("Reminders") {
                Toggle("Daily Wisdom Reminder", isOn: $dailyReminder)
                    .tint(.saffron)

                if dailyReminder {
                    HStack {
                        Image(systemName: "bell.badge.fill")
                            .foregroundStyle(.saffron)
                        Text("Receive a new verse every morning at 7:00 AM")
                            .font(.caption)
                            .foregroundStyle(.secondary)
                    }
                }
            }

            // AI Advisor
            Section("Gita Advisor (AI)") {
                if apiKey.isEmpty {
                    VStack(alignment: .leading, spacing: 8) {
                        HStack {
                            Image(systemName: "exclamationmark.triangle.fill")
                                .foregroundStyle(.warmAmber)
                            Text("API Key Required for Full Advisor")
                                .font(.subheadline)
                                .fontWeight(.medium)
                        }

                        Text("Without an API key, the Advisor provides offline wisdom from curated verses. With a Claude API key, you get personalised, conversational guidance.")
                            .font(.caption)
                            .foregroundStyle(.secondary)

                        Button("Add API Key") {
                            showAPIKeyField = true
                        }
                        .font(.caption)
                        .foregroundStyle(.saffron)
                    }
                } else {
                    HStack {
                        Image(systemName: "checkmark.circle.fill")
                            .foregroundStyle(.forestGreen)
                        Text("API Key Configured")
                            .font(.subheadline)
                        Spacer()
                        Button("Change") {
                            showAPIKeyField = true
                        }
                        .font(.caption)
                        .foregroundStyle(.saffron)
                    }
                }

                if showAPIKeyField {
                    VStack(alignment: .leading, spacing: 6) {
                        SecureField("sk-ant-...", text: $apiKey)
                            .font(.system(.caption, design: .monospaced))
                            .textFieldStyle(.roundedBorder)
                            .autocorrectionDisabled()
                            .textInputAutocapitalization(.never)

                        Text("Your key is stored locally on your device only.")
                            .font(.caption2)
                            .foregroundStyle(.secondary)
                    }
                }

                HStack {
                    Text("Conversations Saved")
                    Spacer()
                    Text("\(conversationService.conversations.count)")
                        .foregroundStyle(.secondary)
                }
            }

            // General
            Section("General") {
                Toggle("Haptic Feedback", isOn: $hapticFeedback)
                    .tint(.saffron)
            }

            // About
            Section("About") {
                HStack {
                    Text("Source")
                    Spacer()
                    Text("Bhagavad Gita (Shrimad Bhagavad Gita)")
                        .font(.caption)
                        .foregroundStyle(.secondary)
                }

                HStack {
                    Text("Translations")
                    Spacer()
                    Text("Multiple scholarly sources")
                        .font(.caption)
                        .foregroundStyle(.secondary)
                }

                HStack {
                    Text("Commentary")
                    Spacer()
                    Text("Original corporate & family wisdom")
                        .font(.caption)
                        .foregroundStyle(.secondary)
                }
            }

            // Philosophy
            Section {
                VStack(spacing: 8) {
                    Text("कर्मण्येवाधिकारस्ते मा फलेषु कदाचन")
                        .font(.system(size: 14, design: .serif))
                        .multilineTextAlignment(.center)

                    Text("You have the right to work, but never to the fruit of work.")
                        .font(.caption)
                        .foregroundStyle(.secondary)
                        .italic()
                        .multilineTextAlignment(.center)

                    Text("— Bhagavad Gita 2.47")
                        .font(.caption2)
                        .foregroundStyle(.saffron)
                }
                .frame(maxWidth: .infinity)
                .padding(.vertical, 8)
            }
        }
        .navigationTitle("Settings")
        .navigationBarTitleDisplayMode(.inline)
    }
}
