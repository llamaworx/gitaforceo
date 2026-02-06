import SwiftUI

struct VerseDetailView: View {
    let verse: Verse
    @EnvironmentObject var bookmarkService: BookmarkService
    @EnvironmentObject var audioPlayerVM: AudioPlayerViewModel
    @State private var showNoteEditor = false
    @State private var noteText = ""
    @State private var selectedTab: WisdomTab = .translation

    enum WisdomTab: String, CaseIterable {
        case translation = "Translation"
        case corporate = "Corporate"
        case family = "Family"
    }

    var body: some View {
        ScrollView {
            VStack(spacing: 20) {
                verseHeader
                sanskritSection
                wisdomTabs
                wisdomContent
                themeTags
                actionsSection
                noteSection
            }
            .padding(.bottom, 100)
        }
        .background(Color(.systemGroupedBackground))
        .navigationTitle(verse.displayReference)
        .navigationBarTitleDisplayMode(.inline)
        .toolbar {
            ToolbarItemGroup(placement: .navigationBarTrailing) {
                Button {
                    bookmarkService.toggleBookmark(verse.id)
                } label: {
                    Image(systemName: bookmarkService.isBookmarked(verse.id) ? "bookmark.fill" : "bookmark")
                        .foregroundStyle(.saffron)
                }

                ShareLink(item: shareText) {
                    Image(systemName: "square.and.arrow.up")
                }
            }
        }
        .onAppear {
            bookmarkService.markAsRead(verse.id)
            noteText = bookmarkService.getNote(for: verse.id)
        }
    }

    // MARK: - Verse Header

    private var verseHeader: some View {
        VStack(spacing: 8) {
            Text(verse.displayReference)
                .font(.caption)
                .fontWeight(.semibold)
                .foregroundStyle(.saffron)
                .padding(.horizontal, 12)
                .padding(.vertical, 4)
                .background(Color.saffron.opacity(0.1), in: Capsule())

            if let chapter = GitaData.chapters.first(where: { $0.id == verse.chapterId }) {
                Text(chapter.name)
                    .font(.subheadline)
                    .foregroundStyle(.secondary)
            }
        }
        .padding(.top, 8)
    }

    // MARK: - Sanskrit

    private var sanskritSection: some View {
        VStack(spacing: 12) {
            Text(verse.sanskrit)
                .font(.system(size: 18, design: .serif))
                .multilineTextAlignment(.center)
                .foregroundStyle(.primary)
                .lineSpacing(6)

            Divider()
                .frame(width: 40)

            Text(verse.transliteration)
                .font(.caption)
                .foregroundStyle(.secondary)
                .italic()
                .multilineTextAlignment(.center)
        }
        .padding(20)
        .frame(maxWidth: .infinity)
        .background(
            RoundedRectangle(cornerRadius: 16)
                .fill(LinearGradient.cardGlow)
                .overlay(
                    RoundedRectangle(cornerRadius: 16)
                        .stroke(Color.saffron.opacity(0.15), lineWidth: 1)
                )
        )
        .padding(.horizontal)
    }

    // MARK: - Tabs

    private var wisdomTabs: some View {
        HStack(spacing: 0) {
            ForEach(WisdomTab.allCases, id: \.self) { tab in
                Button {
                    withAnimation(.easeInOut(duration: 0.2)) {
                        selectedTab = tab
                    }
                } label: {
                    VStack(spacing: 6) {
                        Text(tab.rawValue)
                            .font(.subheadline)
                            .fontWeight(selectedTab == tab ? .semibold : .regular)
                            .foregroundStyle(selectedTab == tab ? .saffron : .secondary)

                        Rectangle()
                            .fill(selectedTab == tab ? Color.saffron : Color.clear)
                            .frame(height: 2)
                    }
                }
                .frame(maxWidth: .infinity)
            }
        }
        .padding(.horizontal)
    }

    // MARK: - Wisdom Content

    @ViewBuilder
    private var wisdomContent: some View {
        VStack(alignment: .leading, spacing: 16) {
            switch selectedTab {
            case .translation:
                WisdomSection(
                    icon: "text.book.closed.fill",
                    title: "Translation",
                    content: verse.translation,
                    color: .deepBlue
                )
                WisdomSection(
                    icon: "text.quote",
                    title: "Commentary",
                    content: verse.commentary,
                    color: .forestGreen
                )
            case .corporate:
                WisdomSection(
                    icon: "building.2.fill",
                    title: "For the Boardroom",
                    content: verse.corporateWisdom,
                    color: .saffron
                )
            case .family:
                WisdomSection(
                    icon: "house.and.flag.fill",
                    title: "For the Home",
                    content: verse.familyWisdom,
                    color: .lotusRose
                )
            }
        }
        .padding(.horizontal)
    }

    // MARK: - Themes

    private var themeTags: some View {
        VStack(alignment: .leading, spacing: 8) {
            Text("Themes")
                .font(.caption)
                .fontWeight(.semibold)
                .foregroundStyle(.secondary)

            FlowLayout(spacing: 8) {
                ForEach(verse.themes) { theme in
                    HStack(spacing: 4) {
                        Image(systemName: theme.icon)
                            .font(.caption2)
                        Text(theme.rawValue)
                            .font(.caption2)
                    }
                    .foregroundStyle(theme.color)
                    .padding(.horizontal, 10)
                    .padding(.vertical, 5)
                    .background(theme.color.opacity(0.1), in: Capsule())
                }
            }
        }
        .padding()
        .frame(maxWidth: .infinity, alignment: .leading)
        .background(.background, in: RoundedRectangle(cornerRadius: 12))
        .padding(.horizontal)
    }

    // MARK: - Actions

    private var actionsSection: some View {
        HStack(spacing: 16) {
            Button {
                audioPlayerVM.playVerse(verse)
            } label: {
                Label("Listen", systemImage: "play.circle.fill")
                    .font(.subheadline)
                    .fontWeight(.medium)
                    .foregroundStyle(.white)
                    .frame(maxWidth: .infinity)
                    .padding(.vertical, 12)
                    .background(Color.saffron, in: RoundedRectangle(cornerRadius: 12))
            }

            Button {
                showNoteEditor.toggle()
            } label: {
                Label("Note", systemImage: "square.and.pencil")
                    .font(.subheadline)
                    .fontWeight(.medium)
                    .foregroundStyle(.saffron)
                    .frame(maxWidth: .infinity)
                    .padding(.vertical, 12)
                    .background(Color.saffron.opacity(0.1), in: RoundedRectangle(cornerRadius: 12))
            }
        }
        .padding(.horizontal)
    }

    // MARK: - Notes

    @ViewBuilder
    private var noteSection: some View {
        if showNoteEditor || !noteText.isEmpty {
            VStack(alignment: .leading, spacing: 8) {
                Text("Your Reflections")
                    .font(.caption)
                    .fontWeight(.semibold)
                    .foregroundStyle(.secondary)

                TextEditor(text: $noteText)
                    .frame(minHeight: 80)
                    .padding(8)
                    .background(Color(.systemGray6), in: RoundedRectangle(cornerRadius: 8))
                    .onChange(of: noteText) { _, newValue in
                        bookmarkService.saveNote(for: verse.id, note: newValue)
                    }
            }
            .padding()
            .background(.background, in: RoundedRectangle(cornerRadius: 12))
            .padding(.horizontal)
        }
    }

    private var shareText: String {
        """
        \(verse.displayReference)

        \(verse.translation)

        Corporate Wisdom: \(verse.corporateWisdom)

        — Gita for CEOs
        """
    }
}

// MARK: - Wisdom Section

struct WisdomSection: View {
    let icon: String
    let title: String
    let content: String
    let color: Color

    var body: some View {
        VStack(alignment: .leading, spacing: 10) {
            HStack(spacing: 6) {
                Image(systemName: icon)
                    .foregroundStyle(color)
                Text(title)
                    .font(.subheadline)
                    .fontWeight(.semibold)
                    .foregroundStyle(color)
            }

            Text(content)
                .font(.body)
                .lineSpacing(6)
                .foregroundStyle(.primary.opacity(0.85))
        }
        .padding()
        .frame(maxWidth: .infinity, alignment: .leading)
        .background(.background, in: RoundedRectangle(cornerRadius: 12))
        .shadow(color: .black.opacity(0.03), radius: 4, y: 2)
    }
}

// MARK: - Flow Layout

struct FlowLayout: Layout {
    var spacing: CGFloat = 8

    func sizeThatFits(proposal: ProposedViewSize, subviews: Subviews, cache: inout ()) -> CGSize {
        let result = arrange(proposal: proposal, subviews: subviews)
        return result.size
    }

    func placeSubviews(in bounds: CGRect, proposal: ProposedViewSize, subviews: Subviews, cache: inout ()) {
        let result = arrange(proposal: proposal, subviews: subviews)
        for (index, subview) in subviews.enumerated() {
            if index < result.positions.count {
                subview.place(
                    at: CGPoint(
                        x: bounds.minX + result.positions[index].x,
                        y: bounds.minY + result.positions[index].y
                    ),
                    proposal: ProposedViewSize(subview.sizeThatFits(.unspecified))
                )
            }
        }
    }

    private func arrange(proposal: ProposedViewSize, subviews: Subviews) -> (positions: [CGPoint], size: CGSize) {
        let maxWidth = proposal.width ?? .infinity
        var positions: [CGPoint] = []
        var x: CGFloat = 0
        var y: CGFloat = 0
        var rowHeight: CGFloat = 0
        var maxX: CGFloat = 0

        for subview in subviews {
            let size = subview.sizeThatFits(.unspecified)
            if x + size.width > maxWidth, x > 0 {
                x = 0
                y += rowHeight + spacing
                rowHeight = 0
            }
            positions.append(CGPoint(x: x, y: y))
            rowHeight = max(rowHeight, size.height)
            x += size.width + spacing
            maxX = max(maxX, x)
        }

        return (positions, CGSize(width: maxX, height: y + rowHeight))
    }
}
