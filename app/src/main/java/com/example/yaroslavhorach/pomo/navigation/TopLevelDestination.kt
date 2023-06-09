package com.example.yaroslavhorach.pomo.navigation

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.example.yaroslavhorach.designsystem.theme.graphics.PomoIcons
import com.example.yaroslavhorach.designsystem.theme.secondaryIcon
import com.example.yaroslavhorach.home.navigation.homeNavigationRoute
import com.example.yaroslavhorach.pomo.R

/**
 * Type for the top level destinations in the application.
 */
sealed class TopLevelDestination(
    val iconResId: Int,
    val titleTextResId: Int,
    val navigationRoute: String?
) {
    @Composable
    fun getUnselectedIconColor(): Color = MaterialTheme.colorScheme.secondaryIcon()

    @Composable
    fun getSelectedIconColor(): Color = MaterialTheme.colorScheme.primary

    object Home : TopLevelDestination(PomoIcons.Home, R.string.nav_bar_home_title, homeNavigationRoute)

    object Calendar : TopLevelDestination(PomoIcons.Calendar, R.string.nav_bar_calendar_title, null)

    object Statistics : TopLevelDestination(PomoIcons.Chart, R.string.nav_bar_statistics_title, null)

    object Profile : TopLevelDestination(PomoIcons.User, R.string.nav_bar_profile_title, null)
}