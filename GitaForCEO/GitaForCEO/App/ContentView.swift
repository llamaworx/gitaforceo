import SwiftUI

struct ContentView: View {
    @StateObject private var viewModel = GitaViewModel()
    @StateObject private var audioService = AudioService()
    @StateObject private var bookmarkService = BookmarkService()
    @State private var audioPlayerVM: AudioPlayerViewModel?
    @State private var selectedTab: AppTab = .home

    enum AppTab: String {
        case home = "Home"
        case chapters = "Chapters"
        case themes = "Themes"
        case search = "Search"
    }

    var body: some View {
        ZStack(alignment: .bottom) {
            TabView(selection: $selectedTab) {
                HomeView()
                    .tag(AppTab.home)
                    .tabItem {
                        Label("Home", systemImage: "om")
                    }

                ChapterListView()
                    .tag(AppTab.chapters)
                    .tabItem {
                        Label("Chapters", systemImage: "book.fill")
                    }

                ThemeGalleryView()
                    .tag(AppTab.themes)
                    .tabItem {
                        Label("Themes", systemImage: "square.grid.2x2.fill")
                    }

                SearchView()
                    .tag(AppTab.search)
                    .tabItem {
                        Label("Search", systemImage: "magnifyingglass")
                    }
            }
            .tint(.saffron)

            // Floating audio player
            if let audioPlayerVM {
                AudioPlayerView()
                    .environmentObject(audioPlayerVM)
                    .padding(.bottom, 50)
            }
        }
        .environmentObject(viewModel)
        .environmentObject(bookmarkService)
        .environmentObject(audioPlayerVM ?? AudioPlayerViewModel(audioService: audioService))
        .onAppear {
            if audioPlayerVM == nil {
                audioPlayerVM = AudioPlayerViewModel(audioService: audioService)
            }
            configureTabBarAppearance()
        }
    }

    private func configureTabBarAppearance() {
        let appearance = UITabBarAppearance()
        appearance.configureWithDefaultBackground()
        UITabBar.appearance().scrollEdgeAppearance = appearance
        UITabBar.appearance().standardAppearance = appearance
    }
}
