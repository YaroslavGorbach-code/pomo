package com.example.yaroslavhorach.pomo.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.navigation.NavDestination
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navOptions
import com.example.yaroslavhorach.createTask.navigation.navigateToCreateTask
import com.example.yaroslavhorach.home.navigation.navigateToHome
import com.example.yaroslavhorach.pomo.navigation.TopLevelDestination
import kotlinx.coroutines.CoroutineScope

@Composable
fun rememberNiaAppState(
    coroutineScope: CoroutineScope = rememberCoroutineScope(),
    navController: NavHostController = rememberNavController(),
): NiaAppState {
    return remember(navController, coroutineScope) {
        NiaAppState(navController, coroutineScope)
    }
}

@Stable
class NiaAppState(
    val navController: NavHostController,
    val coroutineScope: CoroutineScope
) {
    val currentDestination: NavDestination?
        @Composable get() = navController
            .currentBackStackEntryAsState().value?.destination

    val currentTopLevelDestination: TopLevelDestination?
        @Composable get() = when (currentDestination?.route) {
            else -> null
        }

    val isBottomBarAndTopBarVisible: Boolean
        @Composable get() = topLevelDestinations.map { it.navigationRoute }
            .contains(currentDestination?.route.toString())

    val topLevelDestinations: List<TopLevelDestination> = listOf(
        TopLevelDestination.Home,
        TopLevelDestination.Calendar,
        TopLevelDestination.Statistics,
        TopLevelDestination.Profile
    )

    /**
     * UI logic for navigating to a top level destination in the app. Top level destinations have
     * only one copy of the destination of the back stack, and save and restore state whenever you
     * navigate to and from it.
     *
     * @param topLevelDestination: The destination the app needs to navigate to.
     */
    fun navigateToTopLevelDestination(topLevelDestination: TopLevelDestination) {
        if (navController.currentDestination?.route == topLevelDestination.navigationRoute) return

        val topLevelNavOptions = navOptions {
            // Pop up to the start destination of the graph to
            // avoid building up a large stack of destinations
            // on the back stack as users select items
            popUpTo(navController.graph.findStartDestination().id) {
                saveState = true
            }
            launchSingleTop = true
            restoreState = true
        }

        when (topLevelDestination) {
            TopLevelDestination.Calendar -> {}
            TopLevelDestination.Home -> navController.navigateToHome()
            TopLevelDestination.Profile -> {}
            TopLevelDestination.Statistics -> {}
        }
    }

    fun navigateToAddTask() {
        navController.navigateToCreateTask()
    }
}