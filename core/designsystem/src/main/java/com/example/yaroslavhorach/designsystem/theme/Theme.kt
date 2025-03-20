package com.example.yaroslavhorach.designsystem.theme

import android.os.Build
import androidx.annotation.ChecksSdkIntAtLeast
import androidx.annotation.VisibleForTesting
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext

@VisibleForTesting
val LightDefaultColorScheme = lightColorScheme(
    background = Cultured,
    onBackground = BrightGray,
    surface = White,
    secondary = Crayola,
    primary = Crayola,
    primaryContainer = Calamansi,
    onPrimaryContainer = Crayola
)

@Composable
fun ColorScheme.primaryVariant() = if (isSystemInDarkTheme()) Crayola15 else Crayola15
@Composable
fun ColorScheme.primaryInactive() = if (isSystemInDarkTheme()) FuzzyWuzzy else FuzzyWuzzy
@Composable
fun ColorScheme.typoPrimary() = if (isSystemInDarkTheme()) VampireBlack else VampireBlack
@Composable
fun ColorScheme.typoSecondary() = if (isSystemInDarkTheme()) DavysGrey else DavysGrey
@Composable
fun ColorScheme.controlPrimaryTypo() = if (isSystemInDarkTheme()) White else White
@Composable
fun ColorScheme.controlSecondaryTypo() = if (isSystemInDarkTheme()) Crayola else Crayola
@Composable
fun ColorScheme.divider() = if (isSystemInDarkTheme()) Black10 else Black10
@Composable
fun ColorScheme.secondaryIcon() = if (isSystemInDarkTheme()) AmericanSilver else AmericanSilver
@Composable
fun ColorScheme.primaryIcon() = if (isSystemInDarkTheme()) DarkSilver else DarkSilver
@Composable
fun ColorScheme.primaryVariantInactive() = if (isSystemInDarkTheme()) FuzzyWuzzy10 else FuzzyWuzzy10
@Composable
fun ColorScheme.alert() = if (isSystemInDarkTheme()) Alert else Alert

/**
 * Dark default theme color scheme
 */
@VisibleForTesting
val DarkDefaultColorScheme = darkColorScheme(
    background = Cultured,
    onBackground = BrightGray,
    surface = White,
    secondary = Crayola,
    primary = Crayola
)

@Composable
fun PomoTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    dynamicColor: Boolean = false,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && supportsDynamicTheming() -> {
            if (darkTheme) {
                dynamicDarkColorScheme(LocalContext.current)
            } else {
                dynamicLightColorScheme(LocalContext.current)
            }
        }
        darkTheme -> DarkDefaultColorScheme
        else -> LightDefaultColorScheme
    }

    MaterialTheme(colorScheme = colorScheme, content = content)
}
@ChecksSdkIntAtLeast(api = Build.VERSION_CODES.S)
fun supportsDynamicTheming() = Build.VERSION.SDK_INT >= Build.VERSION_CODES.S
