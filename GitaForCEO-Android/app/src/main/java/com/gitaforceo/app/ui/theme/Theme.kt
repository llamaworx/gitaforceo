package com.gitaforceo.app.ui.theme

import android.app.Activity
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

private val LightColorScheme = lightColorScheme(
    primary = Saffron,
    onPrimary = Color.White,
    primaryContainer = Saffron.copy(alpha = 0.12f),
    onPrimaryContainer = Saffron,
    secondary = DeepBlue,
    onSecondary = Color.White,
    secondaryContainer = DeepBlue.copy(alpha = 0.12f),
    onSecondaryContainer = DeepBlue,
    tertiary = SacredGold,
    background = LightBackground,
    onBackground = TextPrimary,
    surface = LightSurface,
    onSurface = TextPrimary,
    surfaceVariant = LightSurfaceVariant,
    onSurfaceVariant = TextSecondary,
    outline = Color(0xFFD1CCC0),
)

private val DarkColorScheme = darkColorScheme(
    primary = Saffron,
    onPrimary = Color.Black,
    primaryContainer = Saffron.copy(alpha = 0.2f),
    onPrimaryContainer = Saffron,
    secondary = DeepBlue,
    onSecondary = Color.White,
    tertiary = SacredGold,
    background = DarkSurface,
    onBackground = Color.White,
    surface = DarkCard,
    onSurface = Color.White,
    surfaceVariant = DarkCard,
    onSurfaceVariant = Color(0xFFA0A0B0),
)

@Composable
fun GitaForCEOTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme = if (darkTheme) DarkColorScheme else LightColorScheme

    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            window.statusBarColor = DeepBlue.toArgb()
            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = false
        }
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = GitaTypography,
        content = content
    )
}
