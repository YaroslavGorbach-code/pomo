package com.example.yaroslavhorach.pomo.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import com.example.yaroslavhorach.designsystem.theme.components.AddTaskPomoNavigationBarItem
import com.example.yaroslavhorach.designsystem.theme.components.PomoBackground
import com.example.yaroslavhorach.designsystem.theme.components.PomoNavigationBar
import com.example.yaroslavhorach.designsystem.theme.components.PomoNavigationBarItem
import com.example.yaroslavhorach.pomo.navigation.PomoNavHost
import com.example.yaroslavhorach.pomo.navigation.TopLevelDestination

@OptIn(ExperimentalMaterial3Api::class, ExperimentalLayoutApi::class)
@Composable
fun PomoApp(
    appState: NiaAppState = rememberNiaAppState(),
) {
    PomoBackground {
        val snackbarHostState = remember { SnackbarHostState() }

        Scaffold(
            containerColor = Color.Transparent,
            contentWindowInsets = WindowInsets(0, 0, 0, 0),
            snackbarHost = { SnackbarHost(snackbarHostState) },
            bottomBar = {
                PomoBottomBar(
                    destinations = appState.topLevelDestinations,
                    onNavigateToDestination = appState::navigateToTopLevelDestination,
                    currentDestination = appState.currentDestination,
                    onNavigateToCreateTask = {}
                )
            },
        ) { padding ->
            Box(
                Modifier
                    .fillMaxSize()
                    .padding(padding)
                    .consumeWindowInsets(padding)
                    .windowInsetsPadding(
                        WindowInsets.safeDrawing.only(
                            WindowInsetsSides.Horizontal,
                        ),
                    ),
            ) {
                PomoNavHost(appState.navController)
            }
        }
    }
}

@Composable
private fun PomoBottomBar(
    destinations: List<TopLevelDestination>,
    onNavigateToDestination: (TopLevelDestination) -> Unit,
    onNavigateToCreateTask: () -> Unit,
    currentDestination: NavDestination?,
    modifier: Modifier = Modifier,
) {
    PomoNavigationBar(modifier = modifier) {
        destinations.forEach { destination ->
            val selected = currentDestination.isTopLevelDestinationInHierarchy(destination)

            if (destination is TopLevelDestination.Calendar){
                PomoNavigationBarItem(
                    selected = selected,
                    onClick = { onNavigateToDestination(destination) },
                    icon = {
                        if (selected) {
                            Icon(
                                painter = painterResource(id = destination.iconResId),
                                contentDescription = null,
                                tint = destination.getSelectedIconColor()
                            )
                        } else {
                            Icon(
                                painter = painterResource(id = destination.iconResId),
                                contentDescription = null,
                                tint = destination.getUnselectedIconColor()
                            )
                        }
                    },
                    label = { Text(stringResource(destination.titleTextResId)) },
                )

                AddTaskPomoNavigationBarItem {

                }
            } else {
                PomoNavigationBarItem(
                    selected = selected,
                    onClick = { onNavigateToDestination(destination) },
                    icon = {
                        if (selected) {
                            Icon(
                                painter = painterResource(id = destination.iconResId),
                                contentDescription = null,
                                tint = destination.getSelectedIconColor()
                            )
                        } else {
                            Icon(
                                painter = painterResource(id = destination.iconResId),
                                contentDescription = null,
                                tint = destination.getUnselectedIconColor()
                            )
                        }
                    },
                    label = { Text(stringResource(destination.titleTextResId)) },
                )
            }
        }
    }
}

private fun NavDestination?.isTopLevelDestinationInHierarchy(destination: TopLevelDestination) =
    this?.hierarchy?.any {
        it.route?.contains(destination::class.java.name, true) ?: false
    } ?: false
