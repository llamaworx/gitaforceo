import SwiftUI

extension Color {
    // Primary palette — inspired by Indian spiritual aesthetics
    static let saffron = Color(red: 0.96, green: 0.62, blue: 0.15)
    static let deepBlue = Color(red: 0.12, green: 0.20, blue: 0.42)
    static let sacredGold = Color(red: 0.81, green: 0.68, blue: 0.25)
    static let lotusRose = Color(red: 0.82, green: 0.42, blue: 0.47)
    static let forestGreen = Color(red: 0.22, green: 0.48, blue: 0.35)
    static let warmAmber = Color(red: 0.87, green: 0.52, blue: 0.18)

    // Backgrounds
    static let parchment = Color(red: 0.98, green: 0.96, blue: 0.91)
    static let darkSurface = Color(red: 0.09, green: 0.09, blue: 0.13)
    static let cardBackground = Color(red: 0.96, green: 0.94, blue: 0.88)
    static let darkCard = Color(red: 0.14, green: 0.14, blue: 0.19)

    // Accent gradients
    static let sunriseGradient = LinearGradient(
        colors: [.saffron, .sacredGold],
        startPoint: .topLeading,
        endPoint: .bottomTrailing
    )

    static let twilightGradient = LinearGradient(
        colors: [.deepBlue, Color(red: 0.25, green: 0.15, blue: 0.45)],
        startPoint: .topLeading,
        endPoint: .bottomTrailing
    )

    static let serenityGradient = LinearGradient(
        colors: [
            Color(red: 0.96, green: 0.62, blue: 0.15).opacity(0.8),
            Color(red: 0.82, green: 0.42, blue: 0.47).opacity(0.6)
        ],
        startPoint: .top,
        endPoint: .bottom
    )
}

extension LinearGradient {
    static let heroBackground = LinearGradient(
        colors: [
            Color(red: 0.12, green: 0.20, blue: 0.42),
            Color(red: 0.18, green: 0.12, blue: 0.35),
            Color(red: 0.08, green: 0.08, blue: 0.20)
        ],
        startPoint: .topLeading,
        endPoint: .bottomTrailing
    )

    static let cardGlow = LinearGradient(
        colors: [
            Color.saffron.opacity(0.15),
            Color.sacredGold.opacity(0.08),
            Color.clear
        ],
        startPoint: .topLeading,
        endPoint: .bottomTrailing
    )
}
