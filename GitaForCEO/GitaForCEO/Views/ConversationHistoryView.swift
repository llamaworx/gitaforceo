import SwiftUI

struct ConversationHistoryView: View {
    @EnvironmentObject var conversationService: ConversationService
    @Environment(\.dismiss) private var dismiss

    var body: some View {
        NavigationStack {
            Group {
                if conversationService.conversations.isEmpty {
                    emptyState
                } else {
                    conversationList
                }
            }
            .background(Color(.systemGroupedBackground))
            .navigationTitle("Past Conversations")
            .navigationBarTitleDisplayMode(.inline)
            .toolbar {
                ToolbarItem(placement: .navigationBarTrailing) {
                    Button("Done") { dismiss() }
                }
            }
        }
    }

    private var emptyState: some View {
        VStack(spacing: 16) {
            Spacer()

            Image(systemName: "bubble.left.and.text.bubble.right")
                .font(.system(size: 48))
                .foregroundStyle(.secondary.opacity(0.4))

            Text("No Conversations Yet")
                .font(.headline)
                .foregroundStyle(.secondary)

            Text("Start a conversation with your Gita Advisor\nand it will appear here for future reference.")
                .font(.subheadline)
                .foregroundStyle(.secondary.opacity(0.7))
                .multilineTextAlignment(.center)

            Spacer()
        }
    }

    private var conversationList: some View {
        List {
            ForEach(conversationService.conversations) { conversation in
                Button {
                    conversationService.currentConversation = conversation
                    dismiss()
                } label: {
                    ConversationRow(conversation: conversation)
                }
                .buttonStyle(.plain)
            }
            .onDelete(perform: deleteConversation)
        }
        .listStyle(.insetGrouped)
    }

    private func deleteConversation(at offsets: IndexSet) {
        for index in offsets {
            let conversation = conversationService.conversations[index]
            conversationService.deleteConversation(conversation.id)
        }
    }
}

struct ConversationRow: View {
    let conversation: Conversation

    var body: some View {
        HStack(spacing: 12) {
            ZStack {
                Circle()
                    .fill(scenarioColor.opacity(0.12))
                    .frame(width: 44, height: 44)

                if let scenario = conversation.scenario {
                    Image(systemName: scenario.icon)
                        .foregroundStyle(scenarioColor)
                } else {
                    Image(systemName: "bubble.left.fill")
                        .foregroundStyle(scenarioColor)
                }
            }

            VStack(alignment: .leading, spacing: 4) {
                Text(conversation.title)
                    .font(.subheadline)
                    .fontWeight(.semibold)
                    .foregroundStyle(.primary)
                    .lineLimit(1)

                if let scenario = conversation.scenario {
                    Text(scenario.category.rawValue)
                        .font(.caption2)
                        .foregroundStyle(.saffron)
                }

                HStack(spacing: 8) {
                    Text("\(conversation.messageCount) messages")
                        .font(.caption2)
                        .foregroundStyle(.secondary)

                    Text("·")
                        .foregroundStyle(.secondary)

                    Text(conversation.updatedAt, style: .relative)
                        .font(.caption2)
                        .foregroundStyle(.secondary)
                }
            }

            Spacer()

            Image(systemName: "chevron.right")
                .font(.caption2)
                .foregroundStyle(.secondary)
        }
        .padding(.vertical, 4)
    }

    private var scenarioColor: Color {
        guard let category = conversation.scenario?.category else { return .saffron }
        switch category {
        case .boardroom: return .deepBlue
        case .leadership: return .saffron
        case .personal: return .lotusRose
        case .crisis: return .warmAmber
        }
    }
}
