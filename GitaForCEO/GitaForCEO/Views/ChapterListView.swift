import SwiftUI

struct ChapterListView: View {
    @EnvironmentObject var viewModel: GitaViewModel
    @State private var expandedChapter: Int?

    var body: some View {
        NavigationStack {
            ScrollView {
                VStack(spacing: 16) {
                    headerView

                    ForEach(viewModel.chapters) { chapter in
                        ChapterRow(
                            chapter: chapter,
                            isExpanded: expandedChapter == chapter.id,
                            onTap: {
                                withAnimation(.easeInOut(duration: 0.3)) {
                                    expandedChapter = expandedChapter == chapter.id ? nil : chapter.id
                                }
                            }
                        )
                    }
                }
                .padding(.bottom, 100)
            }
            .background(Color(.systemGroupedBackground))
            .navigationTitle("Chapters")
        }
    }

    private var headerView: some View {
        VStack(spacing: 8) {
            Text("The 18 Chapters")
                .font(.title3)
                .fontWeight(.semibold)

            Text("Each chapter reveals a different path of yoga — from action to devotion to knowledge")
                .font(.subheadline)
                .foregroundStyle(.secondary)
                .multilineTextAlignment(.center)
                .padding(.horizontal, 32)
        }
        .padding(.top, 8)
    }
}

struct ChapterRow: View {
    let chapter: Chapter
    let isExpanded: Bool
    let onTap: () -> Void

    var body: some View {
        VStack(spacing: 0) {
            // Header
            Button(action: onTap) {
                HStack(spacing: 16) {
                    ZStack {
                        Circle()
                            .fill(Color.saffron.opacity(0.15))
                            .frame(width: 48, height: 48)
                        Text("\(chapter.id)")
                            .font(.title3)
                            .fontWeight(.bold)
                            .foregroundStyle(.saffron)
                    }

                    VStack(alignment: .leading, spacing: 4) {
                        Text(chapter.name)
                            .font(.headline)
                            .foregroundStyle(.primary)

                        Text(chapter.sanskritName)
                            .font(.caption)
                            .foregroundStyle(.secondary)
                            .italic()
                    }

                    Spacer()

                    Image(systemName: isExpanded ? "chevron.up" : "chevron.down")
                        .font(.caption)
                        .foregroundStyle(.secondary)
                }
                .padding()
            }
            .buttonStyle(.plain)

            // Expanded Content
            if isExpanded {
                VStack(alignment: .leading, spacing: 12) {
                    Divider()

                    Text(chapter.summary)
                        .font(.subheadline)
                        .foregroundStyle(.secondary)
                        .lineSpacing(4)

                    // CEO Insight
                    VStack(alignment: .leading, spacing: 8) {
                        HStack {
                            Image(systemName: "lightbulb.fill")
                                .foregroundStyle(.saffron)
                            Text("CEO Insight")
                                .font(.caption)
                                .fontWeight(.semibold)
                                .foregroundStyle(.saffron)
                        }

                        Text(chapter.ceoInsight)
                            .font(.subheadline)
                            .italic()
                            .foregroundStyle(.primary.opacity(0.8))
                    }
                    .padding()
                    .background(Color.saffron.opacity(0.06), in: RoundedRectangle(cornerRadius: 12))

                    // Verse List
                    VStack(alignment: .leading, spacing: 8) {
                        Text("\(chapter.verses.count) Key Verses")
                            .font(.caption)
                            .fontWeight(.semibold)
                            .foregroundStyle(.secondary)

                        ForEach(chapter.verses) { verse in
                            NavigationLink(destination: VerseDetailView(verse: verse)) {
                                HStack {
                                    Text(verse.displayReference)
                                        .font(.caption)
                                        .fontWeight(.semibold)
                                        .foregroundStyle(.saffron)
                                        .frame(width: 60, alignment: .leading)

                                    Text(verse.translation)
                                        .font(.caption)
                                        .foregroundStyle(.primary)
                                        .lineLimit(2)

                                    Spacer()

                                    Image(systemName: "chevron.right")
                                        .font(.caption2)
                                        .foregroundStyle(.secondary)
                                }
                                .padding(.vertical, 4)
                            }
                            .buttonStyle(.plain)

                            if verse.id != chapter.verses.last?.id {
                                Divider()
                            }
                        }
                    }
                }
                .padding(.horizontal)
                .padding(.bottom)
                .transition(.opacity.combined(with: .move(edge: .top)))
            }
        }
        .background(.background, in: RoundedRectangle(cornerRadius: 16))
        .shadow(color: .black.opacity(0.03), radius: 4, y: 2)
        .padding(.horizontal)
    }
}
