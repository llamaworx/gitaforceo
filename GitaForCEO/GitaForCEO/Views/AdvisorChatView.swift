import SwiftUI
import AVFoundation

struct AdvisorChatView: View {
    @EnvironmentObject var conversationService: ConversationService
    @EnvironmentObject var audioPlayerVM: AudioPlayerViewModel
    @State private var inputText = ""
    @State private var showScenarios = false
    @State private var showHistory = false
    @FocusState private var isInputFocused: Bool

    var body: some View {
        NavigationStack {
            ZStack {
                Color(.systemGroupedBackground).ignoresSafeArea()

                if let conversation = conversationService.currentConversation {
                    chatView(conversation)
                } else {
                    welcomeView
                }
            }
            .navigationTitle("Gita Advisor")
            .navigationBarTitleDisplayMode(.inline)
            .toolbar {
                ToolbarItem(placement: .navigationBarLeading) {
                    Button {
                        showHistory = true
                    } label: {
                        Image(systemName: "clock.arrow.circlepath")
                            .foregroundStyle(.saffron)
                    }
                }
                ToolbarItem(placement: .navigationBarTrailing) {
                    Button {
                        _ = conversationService.startNewConversation()
                    } label: {
                        Image(systemName: "plus.circle.fill")
                            .foregroundStyle(.saffron)
                    }
                }
            }
            .sheet(isPresented: $showScenarios) {
                ScenarioPickerView { scenario in
                    let conversation = conversationService.startNewConversation(scenario: scenario)
                    showScenarios = false
                    Task {
                        await conversationService.sendMessage(scenario.prompt)
                    }
                }
            }
            .sheet(isPresented: $showHistory) {
                ConversationHistoryView()
            }
        }
    }

    // MARK: - Welcome View

    private var welcomeView: some View {
        ScrollView {
            VStack(spacing: 28) {
                Spacer(minLength: 40)

                // Advisor avatar
                ZStack {
                    Circle()
                        .fill(LinearGradient.heroBackground)
                        .frame(width: 100, height: 100)

                    Text("🙏")
                        .font(.system(size: 44))
                }

                VStack(spacing: 8) {
                    Text("Namaste")
                        .font(.system(size: 32, weight: .light, design: .serif))

                    Text("Your Gita Advisor")
                        .font(.headline)
                        .foregroundStyle(.saffron)

                    Text("Ask me anything about leadership, ethics,\ndecision-making, family, or inner peace.\nI'll guide you with the Gita's timeless wisdom.")
                        .font(.subheadline)
                        .foregroundStyle(.secondary)
                        .multilineTextAlignment(.center)
                        .padding(.horizontal, 32)
                }

                // Quick start
                Button {
                    let conversation = conversationService.startNewConversation()
                } label: {
                    Label("Start a Conversation", systemImage: "bubble.left.and.bubble.right.fill")
                        .font(.headline)
                        .foregroundStyle(.white)
                        .padding(.horizontal, 32)
                        .padding(.vertical, 14)
                        .background(Color.saffron, in: Capsule())
                }

                // Scenario shortcuts
                VStack(alignment: .leading, spacing: 16) {
                    Text("Or choose a scenario:")
                        .font(.subheadline)
                        .fontWeight(.semibold)
                        .foregroundStyle(.secondary)
                        .padding(.horizontal)

                    ForEach(ScenarioTemplate.Category.allCases, id: \.self) { category in
                        VStack(alignment: .leading, spacing: 8) {
                            Text(category.rawValue)
                                .font(.caption)
                                .fontWeight(.bold)
                                .foregroundStyle(.saffron)
                                .padding(.horizontal)

                            ScrollView(.horizontal, showsIndicators: false) {
                                HStack(spacing: 10) {
                                    ForEach(ScenarioTemplate.allScenarios.filter { $0.category == category }) { scenario in
                                        ScenarioChip(scenario: scenario) {
                                            _ = conversationService.startNewConversation(scenario: scenario)
                                            Task {
                                                await conversationService.sendMessage(scenario.prompt)
                                            }
                                        }
                                    }
                                }
                                .padding(.horizontal)
                            }
                        }
                    }
                }

                Spacer(minLength: 40)
            }
        }
    }

    // MARK: - Chat View

    private func chatView(_ conversation: Conversation) -> some View {
        VStack(spacing: 0) {
            // Messages
            ScrollViewReader { proxy in
                ScrollView {
                    LazyVStack(spacing: 16) {
                        ForEach(conversation.messages.filter { $0.role != .system }) { message in
                            MessageBubble(message: message)
                                .id(message.id)
                        }

                        if conversationService.isLoading {
                            TypingIndicator()
                                .id("typing")
                        }
                    }
                    .padding()
                }
                .onChange(of: conversation.messages.count) { _, _ in
                    withAnimation {
                        if let lastMessage = conversation.messages.last {
                            proxy.scrollTo(lastMessage.id, anchor: .bottom)
                        }
                    }
                }
                .onChange(of: conversationService.isLoading) { _, isLoading in
                    if isLoading {
                        withAnimation {
                            proxy.scrollTo("typing", anchor: .bottom)
                        }
                    }
                }
            }

            Divider()

            // Input bar
            inputBar
        }
    }

    // MARK: - Input Bar

    private var inputBar: some View {
        HStack(spacing: 12) {
            // Scenario picker
            Button {
                showScenarios = true
            } label: {
                Image(systemName: "text.book.closed.fill")
                    .font(.title3)
                    .foregroundStyle(.saffron.opacity(0.7))
            }

            // Text field
            HStack {
                TextField("Ask the Gita...", text: $inputText, axis: .vertical)
                    .lineLimit(1...5)
                    .focused($isInputFocused)
                    .textFieldStyle(.plain)
                    .font(.subheadline)

                if !inputText.isEmpty {
                    Button {
                        sendMessage()
                    } label: {
                        Image(systemName: "arrow.up.circle.fill")
                            .font(.title2)
                            .foregroundStyle(.saffron)
                    }
                }
            }
            .padding(.horizontal, 12)
            .padding(.vertical, 8)
            .background(Color(.systemGray6), in: RoundedRectangle(cornerRadius: 20))
        }
        .padding(.horizontal)
        .padding(.vertical, 8)
        .background(.background)
    }

    private func sendMessage() {
        let text = inputText.trimmingCharacters(in: .whitespacesAndNewlines)
        guard !text.isEmpty else { return }

        inputText = ""
        isInputFocused = false

        Task {
            await conversationService.sendMessage(text)
        }
    }
}

// MARK: - Message Bubble

struct MessageBubble: View {
    let message: ChatMessage
    @EnvironmentObject var audioPlayerVM: AudioPlayerViewModel

    var body: some View {
        HStack(alignment: .top, spacing: 8) {
            if message.role == .advisor {
                advisorAvatar
            }

            if message.role == .user { Spacer(minLength: 60) }

            VStack(alignment: message.role == .user ? .trailing : .leading, spacing: 6) {
                if message.role == .advisor {
                    Text("Gita Advisor")
                        .font(.caption2)
                        .foregroundStyle(.saffron)
                }

                if message.isStreaming {
                    TypingIndicator()
                } else {
                    Text(LocalizedStringKey(message.content))
                        .font(.subheadline)
                        .lineSpacing(4)
                        .foregroundStyle(message.role == .user ? .white : .primary)
                        .textSelection(.enabled)
                }

                // Referenced verse chips
                if !message.referencedVerses.isEmpty {
                    ScrollView(.horizontal, showsIndicators: false) {
                        HStack(spacing: 6) {
                            ForEach(message.referencedVerses, id: \.self) { verseId in
                                if let verse = GitaData.allVerses.first(where: { $0.id == verseId }) {
                                    NavigationLink(destination: VerseDetailView(verse: verse)) {
                                        Text(verse.displayReference)
                                            .font(.caption2)
                                            .fontWeight(.semibold)
                                            .foregroundStyle(.saffron)
                                            .padding(.horizontal, 8)
                                            .padding(.vertical, 3)
                                            .background(Color.saffron.opacity(0.1), in: Capsule())
                                    }
                                }
                            }
                        }
                    }
                }

                // Timestamp
                Text(message.timestamp, style: .time)
                    .font(.caption2)
                    .foregroundStyle(message.role == .user ? .white.opacity(0.7) : .secondary)
            }
            .padding(12)
            .background(
                message.role == .user
                    ? AnyShapeStyle(Color.saffron)
                    : AnyShapeStyle(Color(.systemBackground)),
                in: RoundedRectangle(cornerRadius: 16)
            )
            .shadow(color: .black.opacity(0.04), radius: 4, y: 2)

            if message.role == .advisor { Spacer(minLength: 40) }
        }
    }

    private var advisorAvatar: some View {
        ZStack {
            Circle()
                .fill(LinearGradient.heroBackground)
                .frame(width: 32, height: 32)

            Text("🙏")
                .font(.caption)
        }
    }
}

// MARK: - Typing Indicator

struct TypingIndicator: View {
    @State private var animating = false

    var body: some View {
        HStack(spacing: 4) {
            ForEach(0..<3, id: \.self) { index in
                Circle()
                    .fill(Color.saffron.opacity(0.6))
                    .frame(width: 8, height: 8)
                    .offset(y: animating ? -4 : 4)
                    .animation(
                        .easeInOut(duration: 0.5)
                            .repeatForever(autoreverses: true)
                            .delay(Double(index) * 0.15),
                        value: animating
                    )
            }
        }
        .padding(.vertical, 4)
        .onAppear { animating = true }
    }
}

// MARK: - Scenario Chip

struct ScenarioChip: View {
    let scenario: ScenarioTemplate
    let onTap: () -> Void

    var body: some View {
        Button(action: onTap) {
            HStack(spacing: 8) {
                Image(systemName: scenario.icon)
                    .font(.caption)
                    .foregroundStyle(.saffron)

                VStack(alignment: .leading, spacing: 2) {
                    Text(scenario.title)
                        .font(.caption)
                        .fontWeight(.semibold)
                        .foregroundStyle(.primary)

                    Text(scenario.description)
                        .font(.caption2)
                        .foregroundStyle(.secondary)
                        .lineLimit(1)
                }
            }
            .padding(.horizontal, 12)
            .padding(.vertical, 10)
            .background(.background, in: RoundedRectangle(cornerRadius: 12))
            .shadow(color: .black.opacity(0.04), radius: 4, y: 2)
        }
        .buttonStyle(.plain)
    }
}

// MARK: - Scenario Picker Sheet

struct ScenarioPickerView: View {
    let onSelect: (ScenarioTemplate) -> Void
    @Environment(\.dismiss) private var dismiss

    var body: some View {
        NavigationStack {
            ScrollView {
                VStack(alignment: .leading, spacing: 24) {
                    Text("Choose a scenario that matches your situation. Your Gita Advisor will provide wisdom tailored to this context.")
                        .font(.subheadline)
                        .foregroundStyle(.secondary)
                        .padding(.horizontal)

                    ForEach(ScenarioTemplate.Category.allCases, id: \.self) { category in
                        VStack(alignment: .leading, spacing: 12) {
                            Text(category.rawValue)
                                .font(.headline)
                                .foregroundStyle(.saffron)
                                .padding(.horizontal)

                            ForEach(ScenarioTemplate.allScenarios.filter { $0.category == category }) { scenario in
                                Button {
                                    onSelect(scenario)
                                    dismiss()
                                } label: {
                                    HStack(spacing: 12) {
                                        ZStack {
                                            Circle()
                                                .fill(Color.saffron.opacity(0.12))
                                                .frame(width: 44, height: 44)
                                            Image(systemName: scenario.icon)
                                                .foregroundStyle(.saffron)
                                        }

                                        VStack(alignment: .leading, spacing: 4) {
                                            Text(scenario.title)
                                                .font(.subheadline)
                                                .fontWeight(.semibold)
                                                .foregroundStyle(.primary)

                                            Text(scenario.description)
                                                .font(.caption)
                                                .foregroundStyle(.secondary)
                                        }

                                        Spacer()

                                        Image(systemName: "chevron.right")
                                            .font(.caption)
                                            .foregroundStyle(.secondary)
                                    }
                                    .padding()
                                    .background(.background, in: RoundedRectangle(cornerRadius: 12))
                                    .shadow(color: .black.opacity(0.03), radius: 4, y: 2)
                                }
                                .buttonStyle(.plain)
                                .padding(.horizontal)
                            }
                        }
                    }
                }
                .padding(.bottom, 40)
            }
            .background(Color(.systemGroupedBackground))
            .navigationTitle("Scenarios")
            .navigationBarTitleDisplayMode(.inline)
            .toolbar {
                ToolbarItem(placement: .navigationBarTrailing) {
                    Button("Cancel") { dismiss() }
                }
            }
        }
    }
}
