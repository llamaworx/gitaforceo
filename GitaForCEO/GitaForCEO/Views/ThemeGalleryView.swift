import SwiftUI

struct ThemeGalleryView: View {
    let columns = [
        GridItem(.flexible(), spacing: 16),
        GridItem(.flexible(), spacing: 16)
    ]

    var body: some View {
        ScrollView {
            VStack(spacing: 16) {
                Text("Discover wisdom organised by the themes that matter most to leaders.")
                    .font(.subheadline)
                    .foregroundStyle(.secondary)
                    .multilineTextAlignment(.center)
                    .padding(.horizontal, 32)

                LazyVGrid(columns: columns, spacing: 16) {
                    ForEach(WisdomTheme.allCases) { theme in
                        NavigationLink(destination: ThemeDetailView(theme: theme)) {
                            ThemeCard(theme: theme)
                        }
                        .buttonStyle(.plain)
                    }
                }
                .padding(.horizontal)
            }
            .padding(.bottom, 100)
        }
        .background(Color(.systemGroupedBackground))
        .navigationTitle("Wisdom Themes")
    }
}

struct ThemeCard: View {
    let theme: WisdomTheme

    var body: some View {
        VStack(spacing: 12) {
            ZStack {
                Circle()
                    .fill(theme.color.opacity(0.12))
                    .frame(width: 56, height: 56)
                Image(systemName: theme.icon)
                    .font(.title2)
                    .foregroundStyle(theme.color)
            }

            Text(theme.rawValue)
                .font(.subheadline)
                .fontWeight(.medium)
                .foregroundStyle(.primary)
                .multilineTextAlignment(.center)

            Text("\(GitaData.versesForTheme(theme).count) verses")
                .font(.caption2)
                .foregroundStyle(.secondary)
        }
        .padding(.vertical, 20)
        .frame(maxWidth: .infinity)
        .background(.background, in: RoundedRectangle(cornerRadius: 16))
        .shadow(color: .black.opacity(0.04), radius: 6, y: 3)
    }
}
