import SwiftUI

struct HomeView: View {
    @EnvironmentObject var viewModel: GitaViewModel
    @EnvironmentObject var bookmarkService: BookmarkService
    @EnvironmentObject var audioPlayerVM: AudioPlayerViewModel
    @State private var showDailyWisdom = false

    var body: some View {
        NavigationStack {
            ScrollView {
                VStack(spacing: 24) {
                    heroSection
                    dailyVerseCard
                    quickThemesSection
                    progressSection
                    bookmarkedSection
                }
                .padding(.bottom, 100)
            }
            .background(Color(.systemGroupedBackground))
            .navigationTitle("")
            .toolbar {
                ToolbarItem(placement: .navigationBarTrailing) {
                    NavigationLink(destination: SettingsView()) {
                        Image(systemName: "gearshape.fill")
                            .foregroundStyle(.secondary)
                    }
                }
            }
            .sheet(isPresented: $showDailyWisdom) {
                DailyWisdomView()
            }
        }
    }

    // MARK: - Hero Section

    private var heroSection: some View {
        VStack(spacing: 16) {
            ZStack {
                RoundedRectangle(cornerRadius: 24)
                    .fill(LinearGradient.heroBackground)
                    .frame(height: 200)
                    .overlay(
                        RoundedRectangle(cornerRadius: 24)
                            .fill(LinearGradient.cardGlow)
                    )

                VStack(spacing: 8) {
                    Text("॥ श्रीमद्भगवद्गीता ॥")
                        .font(.subheadline)
                        .foregroundStyle(.white.opacity(0.7))

                    Text(viewModel.greeting)
                        .font(.system(size: 28, weight: .light, design: .serif))
                        .foregroundStyle(.white)

                    Text(viewModel.greetingSubtitle)
                        .font(.subheadline)
                        .foregroundStyle(.white.opacity(0.8))

                    Divider()
                        .frame(width: 60)
                        .background(.white.opacity(0.3))
                        .padding(.vertical, 4)

                    Text(""\(viewModel.dailyQuote)"")
                        .font(.caption)
                        .foregroundStyle(.white.opacity(0.7))
                        .multilineTextAlignment(.center)
                        .padding(.horizontal, 24)
                        .italic()
                }
            }
            .padding(.horizontal)
        }
    }

    // MARK: - Daily Verse Card

    private var dailyVerseCard: some View {
        VStack(alignment: .leading, spacing: 12) {
            HStack {
                Image(systemName: "sun.max.fill")
                    .foregroundStyle(.saffron)
                Text("Today's Wisdom")
                    .font(.headline)
                Spacer()
                Button("Read More") {
                    showDailyWisdom = true
                }
                .font(.caption)
                .foregroundStyle(.saffron)
            }

            let verse = viewModel.dailyVerse
            VStack(alignment: .leading, spacing: 8) {
                Text(verse.displayReference)
                    .font(.caption)
                    .foregroundStyle(.secondary)
                    .fontWeight(.semibold)

                Text(verse.translation)
                    .font(.body)
                    .lineLimit(3)

                HStack(spacing: 12) {
                    Button {
                        audioPlayerVM.playVerse(verse)
                    } label: {
                        Label("Listen", systemImage: "play.circle.fill")
                            .font(.caption)
                            .foregroundStyle(.white)
                            .padding(.horizontal, 12)
                            .padding(.vertical, 6)
                            .background(Color.saffron, in: Capsule())
                    }

                    Button {
                        bookmarkService.toggleBookmark(verse.id)
                    } label: {
                        Label(
                            bookmarkService.isBookmarked(verse.id) ? "Saved" : "Save",
                            systemImage: bookmarkService.isBookmarked(verse.id) ? "bookmark.fill" : "bookmark"
                        )
                        .font(.caption)
                        .foregroundStyle(.saffron)
                        .padding(.horizontal, 12)
                        .padding(.vertical, 6)
                        .background(Color.saffron.opacity(0.1), in: Capsule())
                    }
                }
            }
        }
        .padding()
        .background(.background, in: RoundedRectangle(cornerRadius: 16))
        .shadow(color: .black.opacity(0.05), radius: 8, y: 4)
        .padding(.horizontal)
    }

    // MARK: - Quick Themes

    private var quickThemesSection: some View {
        VStack(alignment: .leading, spacing: 12) {
            HStack {
                Text("Explore by Theme")
                    .font(.headline)
                Spacer()
                NavigationLink("See All") {
                    ThemeGalleryView()
                }
                .font(.caption)
                .foregroundStyle(.saffron)
            }
            .padding(.horizontal)

            ScrollView(.horizontal, showsIndicators: false) {
                HStack(spacing: 12) {
                    ForEach(Array(WisdomTheme.allCases.prefix(6)), id: \.self) { theme in
                        NavigationLink(destination: ThemeDetailView(theme: theme)) {
                            VStack(spacing: 8) {
                                ZStack {
                                    Circle()
                                        .fill(theme.color.opacity(0.15))
                                        .frame(width: 56, height: 56)
                                    Image(systemName: theme.icon)
                                        .font(.title2)
                                        .foregroundStyle(theme.color)
                                }

                                Text(theme.rawValue)
                                    .font(.caption2)
                                    .foregroundStyle(.primary)
                                    .multilineTextAlignment(.center)
                                    .frame(width: 80)
                            }
                        }
                    }
                }
                .padding(.horizontal)
            }
        }
    }

    // MARK: - Progress

    private var progressSection: some View {
        VStack(alignment: .leading, spacing: 12) {
            Text("Your Journey")
                .font(.headline)
                .padding(.horizontal)

            HStack(spacing: 16) {
                StatCard(
                    title: "Verses Read",
                    value: "\(bookmarkService.readCount)",
                    total: "\(bookmarkService.totalVerses)",
                    icon: "book.fill",
                    color: .forestGreen
                )

                StatCard(
                    title: "Bookmarked",
                    value: "\(bookmarkService.bookmarkedVerseIds.count)",
                    total: nil,
                    icon: "bookmark.fill",
                    color: .sacredGold
                )

                StatCard(
                    title: "Progress",
                    value: String(format: "%.0f%%", bookmarkService.progressPercentage),
                    total: nil,
                    icon: "chart.line.uptrend.xyaxis",
                    color: .saffron
                )
            }
            .padding(.horizontal)
        }
    }

    // MARK: - Bookmarked Verses

    @ViewBuilder
    private var bookmarkedSection: some View {
        if !bookmarkService.bookmarkedVerses.isEmpty {
            VStack(alignment: .leading, spacing: 12) {
                Text("Your Bookmarks")
                    .font(.headline)
                    .padding(.horizontal)

                ForEach(bookmarkService.bookmarkedVerses.prefix(3)) { verse in
                    NavigationLink(destination: VerseDetailView(verse: verse)) {
                        BookmarkCard(verse: verse)
                    }
                    .buttonStyle(.plain)
                }
            }
        }
    }
}

// MARK: - Supporting Views

struct StatCard: View {
    let title: String
    let value: String
    let total: String?
    let icon: String
    let color: Color

    var body: some View {
        VStack(spacing: 6) {
            Image(systemName: icon)
                .font(.title3)
                .foregroundStyle(color)

            Text(value)
                .font(.title2)
                .fontWeight(.bold)

            if let total {
                Text("of \(total)")
                    .font(.caption2)
                    .foregroundStyle(.secondary)
            }

            Text(title)
                .font(.caption2)
                .foregroundStyle(.secondary)
        }
        .frame(maxWidth: .infinity)
        .padding(.vertical, 12)
        .background(.background, in: RoundedRectangle(cornerRadius: 12))
        .shadow(color: .black.opacity(0.03), radius: 4, y: 2)
    }
}

struct BookmarkCard: View {
    let verse: Verse

    var body: some View {
        HStack(spacing: 12) {
            RoundedRectangle(cornerRadius: 4)
                .fill(Color.saffron)
                .frame(width: 4)

            VStack(alignment: .leading, spacing: 4) {
                Text(verse.displayReference)
                    .font(.caption)
                    .fontWeight(.semibold)
                    .foregroundStyle(.saffron)

                Text(verse.translation)
                    .font(.subheadline)
                    .lineLimit(2)
                    .foregroundStyle(.primary)
            }

            Spacer()

            Image(systemName: "chevron.right")
                .font(.caption)
                .foregroundStyle(.secondary)
        }
        .padding()
        .background(.background, in: RoundedRectangle(cornerRadius: 12))
        .shadow(color: .black.opacity(0.03), radius: 4, y: 2)
        .padding(.horizontal)
    }
}

// MARK: - Theme Detail View (inline)

struct ThemeDetailView: View {
    let theme: WisdomTheme
    @EnvironmentObject var audioPlayerVM: AudioPlayerViewModel

    var body: some View {
        ScrollView {
            VStack(alignment: .leading, spacing: 16) {
                // Header
                VStack(spacing: 12) {
                    ZStack {
                        Circle()
                            .fill(theme.color.opacity(0.15))
                            .frame(width: 80, height: 80)
                        Image(systemName: theme.icon)
                            .font(.largeTitle)
                            .foregroundStyle(theme.color)
                    }

                    Text(theme.rawValue)
                        .font(.title2)
                        .fontWeight(.semibold)

                    Text(theme.description)
                        .font(.subheadline)
                        .foregroundStyle(.secondary)
                        .multilineTextAlignment(.center)
                        .padding(.horizontal, 32)
                }
                .frame(maxWidth: .infinity)
                .padding(.vertical)

                // Verses
                let verses = GitaData.versesForTheme(theme)
                ForEach(verses) { verse in
                    NavigationLink(destination: VerseDetailView(verse: verse)) {
                        VerseCard(verse: verse)
                    }
                    .buttonStyle(.plain)
                }
            }
            .padding(.bottom, 100)
        }
        .navigationTitle(theme.rawValue)
        .navigationBarTitleDisplayMode(.inline)
    }
}

struct VerseCard: View {
    let verse: Verse

    var body: some View {
        VStack(alignment: .leading, spacing: 8) {
            Text(verse.displayReference)
                .font(.caption)
                .fontWeight(.semibold)
                .foregroundStyle(.saffron)

            Text(verse.translation)
                .font(.subheadline)
                .lineLimit(3)
                .foregroundStyle(.primary)

            HStack {
                ForEach(verse.themes.prefix(3)) { theme in
                    Text(theme.rawValue)
                        .font(.caption2)
                        .foregroundStyle(theme.color)
                        .padding(.horizontal, 8)
                        .padding(.vertical, 2)
                        .background(theme.color.opacity(0.1), in: Capsule())
                }
            }
        }
        .padding()
        .frame(maxWidth: .infinity, alignment: .leading)
        .background(.background, in: RoundedRectangle(cornerRadius: 12))
        .shadow(color: .black.opacity(0.03), radius: 4, y: 2)
        .padding(.horizontal)
    }
}
