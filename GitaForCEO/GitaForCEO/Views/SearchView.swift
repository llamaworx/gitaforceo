import SwiftUI

struct SearchView: View {
    @EnvironmentObject var viewModel: GitaViewModel
    @State private var searchText = ""
    @State private var selectedFilter: SearchFilter = .all

    enum SearchFilter: String, CaseIterable {
        case all = "All"
        case corporate = "Corporate"
        case family = "Family"
        case themes = "Themes"
    }

    var filteredResults: [Verse] {
        guard !searchText.isEmpty else { return [] }
        let query = searchText.lowercased()
        return GitaData.allVerses.filter { verse in
            switch selectedFilter {
            case .all:
                return verse.translation.lowercased().contains(query) ||
                       verse.commentary.lowercased().contains(query) ||
                       verse.corporateWisdom.lowercased().contains(query) ||
                       verse.familyWisdom.lowercased().contains(query) ||
                       verse.displayReference.lowercased().contains(query)
            case .corporate:
                return verse.corporateWisdom.lowercased().contains(query)
            case .family:
                return verse.familyWisdom.lowercased().contains(query)
            case .themes:
                return verse.themes.contains(where: { $0.rawValue.lowercased().contains(query) })
            }
        }
    }

    var body: some View {
        NavigationStack {
            VStack(spacing: 0) {
                // Filter Pills
                ScrollView(.horizontal, showsIndicators: false) {
                    HStack(spacing: 8) {
                        ForEach(SearchFilter.allCases, id: \.self) { filter in
                            Button {
                                selectedFilter = filter
                            } label: {
                                Text(filter.rawValue)
                                    .font(.caption)
                                    .fontWeight(selectedFilter == filter ? .semibold : .regular)
                                    .foregroundStyle(selectedFilter == filter ? .white : .secondary)
                                    .padding(.horizontal, 14)
                                    .padding(.vertical, 7)
                                    .background(
                                        selectedFilter == filter ? Color.saffron : Color(.systemGray5),
                                        in: Capsule()
                                    )
                            }
                        }
                    }
                    .padding(.horizontal)
                    .padding(.vertical, 8)
                }

                if searchText.isEmpty {
                    emptyState
                } else if filteredResults.isEmpty {
                    noResultsState
                } else {
                    resultsList
                }
            }
            .background(Color(.systemGroupedBackground))
            .navigationTitle("Search")
            .searchable(text: $searchText, prompt: "Search verses, wisdom, themes...")
        }
    }

    private var emptyState: some View {
        VStack(spacing: 16) {
            Spacer()
            Image(systemName: "text.magnifyingglass")
                .font(.system(size: 48))
                .foregroundStyle(.secondary.opacity(0.5))

            Text("Search the Gita")
                .font(.headline)
                .foregroundStyle(.secondary)

            Text("Find verses by keywords, themes,\nor search within corporate and family wisdom")
                .font(.subheadline)
                .foregroundStyle(.secondary.opacity(0.7))
                .multilineTextAlignment(.center)

            VStack(alignment: .leading, spacing: 8) {
                Text("Try searching for:")
                    .font(.caption)
                    .foregroundStyle(.secondary)

                ForEach(["leadership", "duty", "meditation", "equanimity", "ethics"], id: \.self) { suggestion in
                    Button {
                        searchText = suggestion
                    } label: {
                        HStack {
                            Image(systemName: "magnifyingglass")
                                .font(.caption)
                            Text(suggestion)
                                .font(.subheadline)
                        }
                        .foregroundStyle(.saffron)
                    }
                }
            }
            .padding()
            .background(.background, in: RoundedRectangle(cornerRadius: 12))
            .padding(.horizontal, 40)

            Spacer()
        }
    }

    private var noResultsState: some View {
        VStack(spacing: 12) {
            Spacer()
            Image(systemName: "doc.text.magnifyingglass")
                .font(.system(size: 40))
                .foregroundStyle(.secondary.opacity(0.5))

            Text("No results for \"\(searchText)\"")
                .font(.headline)
                .foregroundStyle(.secondary)

            Text("Try different keywords or broaden your search filter")
                .font(.subheadline)
                .foregroundStyle(.secondary.opacity(0.7))
            Spacer()
        }
    }

    private var resultsList: some View {
        ScrollView {
            LazyVStack(spacing: 12) {
                Text("\(filteredResults.count) result\(filteredResults.count == 1 ? "" : "s")")
                    .font(.caption)
                    .foregroundStyle(.secondary)
                    .frame(maxWidth: .infinity, alignment: .leading)
                    .padding(.horizontal)

                ForEach(filteredResults) { verse in
                    NavigationLink(destination: VerseDetailView(verse: verse)) {
                        SearchResultCard(verse: verse, query: searchText)
                    }
                    .buttonStyle(.plain)
                }
            }
            .padding(.bottom, 100)
        }
    }
}

struct SearchResultCard: View {
    let verse: Verse
    let query: String

    var body: some View {
        VStack(alignment: .leading, spacing: 8) {
            HStack {
                Text(verse.displayReference)
                    .font(.caption)
                    .fontWeight(.semibold)
                    .foregroundStyle(.saffron)

                Spacer()

                Image(systemName: "chevron.right")
                    .font(.caption2)
                    .foregroundStyle(.secondary)
            }

            Text(verse.translation)
                .font(.subheadline)
                .lineLimit(2)

            HStack(spacing: 4) {
                ForEach(verse.themes.prefix(2)) { theme in
                    Text(theme.rawValue)
                        .font(.caption2)
                        .foregroundStyle(theme.color)
                        .padding(.horizontal, 6)
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
