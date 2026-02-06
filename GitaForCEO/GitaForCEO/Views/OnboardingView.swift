import SwiftUI

struct OnboardingView: View {
    @Binding var hasCompletedOnboarding: Bool
    @State private var currentPage = 0

    private let pages: [OnboardingPage] = [
        OnboardingPage(
            icon: "book.closed.fill",
            iconColor: .saffron,
            title: "Ancient Wisdom,\nModern Leadership",
            subtitle: "The Bhagavad Gita has guided leaders for over 5,000 years. Now, its timeless wisdom is curated specifically for CEOs, board members, and corporate leaders.",
            background: Color.deepBlue
        ),
        OnboardingPage(
            icon: "building.2.crop.circle.fill",
            iconColor: .sacredGold,
            title: "For the Boardroom\n& the Home",
            subtitle: "Every verse comes with two perspectives: corporate wisdom for your professional life, and family wisdom for your personal journey. Because true leadership spans both worlds.",
            background: Color(red: 0.18, green: 0.12, blue: 0.35)
        ),
        OnboardingPage(
            icon: "waveform.circle.fill",
            iconColor: .lotusRose,
            title: "Listen & Reflect",
            subtitle: "Soothing voice narration brings each verse to life. Listen during your morning routine, commute, or quiet evening moments. Let the wisdom sink deep.",
            background: Color(red: 0.15, green: 0.22, blue: 0.38)
        ),
        OnboardingPage(
            icon: "sun.max.trianglebadge.exclamationmark",
            iconColor: .saffron,
            title: "Daily Wisdom\nAwaits You",
            subtitle: "Start each day with a fresh verse and its leadership insight. Bookmark your favourites, add personal reflections, and track your journey through the Gita.",
            background: Color(red: 0.12, green: 0.20, blue: 0.42)
        )
    ]

    var body: some View {
        ZStack {
            // Background
            pages[currentPage].background
                .ignoresSafeArea()
                .animation(.easeInOut(duration: 0.5), value: currentPage)

            VStack(spacing: 32) {
                Spacer()

                // Icon
                ZStack {
                    Circle()
                        .fill(pages[currentPage].iconColor.opacity(0.15))
                        .frame(width: 120, height: 120)

                    Image(systemName: pages[currentPage].icon)
                        .font(.system(size: 48))
                        .foregroundStyle(pages[currentPage].iconColor)
                }
                .transition(.scale.combined(with: .opacity))

                // Text
                VStack(spacing: 16) {
                    Text(pages[currentPage].title)
                        .font(.system(size: 28, weight: .semibold, design: .serif))
                        .foregroundStyle(.white)
                        .multilineTextAlignment(.center)
                        .lineSpacing(4)

                    Text(pages[currentPage].subtitle)
                        .font(.body)
                        .foregroundStyle(.white.opacity(0.75))
                        .multilineTextAlignment(.center)
                        .lineSpacing(4)
                        .padding(.horizontal, 32)
                }

                Spacer()

                // Page indicators
                HStack(spacing: 8) {
                    ForEach(0..<pages.count, id: \.self) { index in
                        Capsule()
                            .fill(index == currentPage ? Color.saffron : Color.white.opacity(0.3))
                            .frame(width: index == currentPage ? 24 : 8, height: 8)
                            .animation(.easeInOut(duration: 0.3), value: currentPage)
                    }
                }

                // Buttons
                VStack(spacing: 12) {
                    if currentPage < pages.count - 1 {
                        Button {
                            withAnimation(.easeInOut(duration: 0.4)) {
                                currentPage += 1
                            }
                        } label: {
                            Text("Continue")
                                .font(.headline)
                                .foregroundStyle(.white)
                                .frame(maxWidth: .infinity)
                                .padding(.vertical, 16)
                                .background(Color.saffron, in: RoundedRectangle(cornerRadius: 14))
                        }

                        Button {
                            hasCompletedOnboarding = true
                        } label: {
                            Text("Skip")
                                .font(.subheadline)
                                .foregroundStyle(.white.opacity(0.6))
                        }
                    } else {
                        Button {
                            hasCompletedOnboarding = true
                        } label: {
                            Text("Begin Your Journey")
                                .font(.headline)
                                .foregroundStyle(.white)
                                .frame(maxWidth: .infinity)
                                .padding(.vertical, 16)
                                .background(Color.saffron, in: RoundedRectangle(cornerRadius: 14))
                        }
                    }
                }
                .padding(.horizontal, 24)
                .padding(.bottom, 40)
            }
        }
        .gesture(
            DragGesture()
                .onEnded { value in
                    if value.translation.width < -50, currentPage < pages.count - 1 {
                        withAnimation { currentPage += 1 }
                    } else if value.translation.width > 50, currentPage > 0 {
                        withAnimation { currentPage -= 1 }
                    }
                }
        )
    }
}

struct OnboardingPage {
    let icon: String
    let iconColor: Color
    let title: String
    let subtitle: String
    let background: Color
}
