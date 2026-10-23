package com.example.ui.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext

private val DarkColorScheme = darkColorScheme(
    primary = Color(0xFF8CD8A4),
    onPrimary = Color(0xFF00391A),
    primaryContainer = ForestGreenPrimaryDark,
    onPrimaryContainer = Color(0xFFA8F5C0),
    secondary = Color(0xFFFFB85C),
    onSecondary = Color(0xFF482900),
    secondaryContainer = Color(0xFF673D00),
    onSecondaryContainer = Color(0xFFFFDDB6),
    tertiary = Color(0xFF8AD1E3),
    onTertiary = Color(0xFF003641),
    background = EcoDarkBackground,
    onBackground = Color(0xFFE1E5DF),
    surface = EcoDarkSurface,
    onSurface = Color(0xFFE1E5DF),
    surfaceVariant = EcoDarkSurfaceVariant,
    onSurfaceVariant = Color(0xFFC1C9BF),
    error = Color(0xFFFFB4AB),
    onError = Color(0xFF690005)
)

private val LightColorScheme = lightColorScheme(
    primary = ForestGreenPrimary,
    onPrimary = Color.White,
    primaryContainer = MintGreenContainer,
    onPrimaryContainer = OnMintGreenContainer,
    secondary = HarvestAmberSecondary,
    onSecondary = Color.White,
    secondaryContainer = HarvestAmberContainer,
    onSecondaryContainer = OnHarvestAmberContainer,
    tertiary = DewTealTertiary,
    onTertiary = Color.White,
    tertiaryContainer = DewTealContainer,
    onTertiaryContainer = OnDewTealContainer,
    background = LightBackground,
    onBackground = Color(0xFF181D18),
    surface = LightSurface,
    onSurface = Color(0xFF181D18),
    surfaceVariant = LightSurfaceVariant,
    onSurfaceVariant = Color(0xFF414A42),
    error = DangerRed,
    onError = Color.White,
    errorContainer = DangerRedContainer,
    onErrorContainer = Color(0xFF410002)
)

enum class AppThemeMode(val title: String, val subtitle: String) {
    SYSTEM("System Default", "Follows device appearance"),
    LIGHT("Light Mode", "Crisp daytime high readability"),
    DARK("Dark Mode", "Battery saving night display")
}

@Composable
fun CropShieldTheme(
    themeMode: AppThemeMode = AppThemeMode.SYSTEM,
    darkTheme: Boolean = when (themeMode) {
        AppThemeMode.LIGHT -> false
        AppThemeMode.DARK -> true
        AppThemeMode.SYSTEM -> isSystemInDarkTheme()
    },
    dynamicColor: Boolean = false, // Set to false to preserve tailored agronomy theme
    content: @Composable () -> Unit,
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }
        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = AppTypography,
        content = content
    )
}
