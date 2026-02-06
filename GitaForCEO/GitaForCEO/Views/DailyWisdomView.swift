import SwiftUI

struct DailyWisdomView: View {
    @EnvironmentObject var viewModel: GitaViewModel
    @EnvironmentObject var audioPlayerVM: AudioPlayerViewModel
    @EnvironmentObject var bookmarkService: BookmarkService
    @Environment(\.dismiss) private var dismiss

    var body: some View {
        NavigationStack {
            ScrollView {
                VStack(spacing: 24) {
                    headerSection
                    verseSection
                    wisdomCards
                }
                .padding(.bottom, 40)
            }
            .background(Color(.systemGroupedBackground))
            .navigationTitle("Daily Wisdom")
            .navigationBarTitleDisplayMode(.inline)
            .toolbar {
                ToolbarItem(placement: .navigationBarTrailing) {
                    Button("Done") { dismiss() }
                }
            }
        }
    }

    private var headerSection: some View {
        ZStack {
            RoundedRectangle(cornerRadius: 20)
                .fill(LinearGradient.heroBackground)
                .frame(height: 160)

            VStack(spacing: 12) {
                Image(systemName: "sun.max.trianglebadge.exclamationmark")
                    .font(.largeTitle)
                    .foregroundStyle(.sacredGold)

                Text(formattedDate)
                    .font(.subheadline)
                    .foregroundStyle(.white.opacity(0.8))

                Text("Your Verse for Today")
                    .font(.title3)
                    .fontWeight(.semibold)
                    .foregroundStyle(.white)
            }
        }
        .padding(.horizontal)
    }

    private var verseSection: some View {
        let verse = viewModel.dailyVerse
        return VStack(spacing: 16) {
            Text(verse.sanskrit)
                .font(.system(size: 20, design: .serif))
                .multilineTextAlignment(.center)
                .lineSpacing(6)
                .padding(.horizontal)

            Divider().frame(width: 60)

            Text(verse.transliteration)
                .font(.caption)
                .italic()
                .foregroundStyle(.secondary)
                .multilineTextAlignment(.center)
                .padding(.horizontal)

            Text(verse.displayReference)
                .font(.caption)
                .fontWeight(.semibold)
                .foregroundStyle(.saffron)
                .padding(.horizontal, 12)
                .padding(.vertical, 4)
                .background(Color.saffron.opacity(0.1), in: Capsule())

            Text(verse.translation)
                .font(.body)
                .lineSpacing(6)
                .multilineTextAlignment(.center)
                .padding(.horizontal, 24)

            // Action buttons
            HStack(spacing: 16) {
                Button {
                    audioPlayerVM.playVerse(verse)
                } label: {
                    Label("Listen", systemImage: "play.circle.fill")
                        .font(.subheadline)
                        .fontWeight(.medium)
                        .foregroundStyle(.white)
                        .padding(.horizontal, 24)
                        .padding(.vertical, 10)
                        .background(Color.saffron, in: Capsule())
                }

                Button {
                    bookmarkService.toggleBookmark(verse.id)
                } label: {
                    Image(systemName: bookmarkService.isBookmarked(verse.id) ? "bookmark.fill" : "bookmark")
                        .font(.title3)
                        .foregroundStyle(.saffron)
                        .padding(10)
                        .background(Color.saffron.opacity(0.1), in: Circle())
                }
            }
        }
        .padding(20)
        .background(.background, in: RoundedRectangle(cornerRadius: 16))
        .shadow(color: .black.opacity(0.05), radius: 8, y: 4)
        .padding(.horizontal)
    }

    private var wisdomCards: some View {
        let verse = viewModel.dailyVerse
        return VStack(spacing: 16) {
            DailyWisdomCard(
                icon: "building.2.fill",
                title: "For the Boardroom",
                content: verse.corporateWisdom,
                color: .saffron
            )

            DailyWisdomCard(
                icon: "house.and.flag.fill",
                title: "For the Home",
                content: verse.familyWisdom,
                color: .lotusRose
            )

            DailyWisdomCard(
                icon: "text.quote",
                title: "Commentary",
                content: verse.commentary,
                color: .forestGreen
            )
        }
    }

    private var formattedDate: String {
        let formatter = DateFormatter()
        formatter.dateFormat = "EEEE, d MMMM yyyy"
        return formatter.string(from: Date())
    }
}

struct DailyWisdomCard: View {
    let icon: String
    let title: String
    let content: String
    let color: Color

    var body: some View {
        VStack(alignment: .leading, spacing: 10) {
            HStack(spacing: 8) {
                Image(systemName: icon)
                    .foregroundStyle(color)
                Text(title)
                    .font(.subheadline)
                    .fontWeight(.semibold)
                    .foregroundStyle(color)
            }

            Text(content)
                .font(.body)
                .lineSpacing(5)
                .foregroundStyle(.primary.opacity(0.85))
        }
        .padding()
        .frame(maxWidth: .infinity, alignment: .leading)
        .background(.background, in: RoundedRectangle(cornerRadius: 12))
        .shadow(color: .black.opacity(0.03), radius: 4, y: 2)
        .padding(.horizontal)
    }
}
