package com.example.yaroslavhorach.pomo.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import com.example.yaroslavhorach.designsystem.theme.PomoTypography
import com.example.yaroslavhorach.designsystem.theme.components.AddTaskPomoNavigationBarItem
import com.example.yaroslavhorach.designsystem.theme.components.PomoBackground
import com.example.yaroslavhorach.designsystem.theme.components.PomoNavigationBar
import com.example.yaroslavhorach.designsystem.theme.components.PomoNavigationBarItem
import com.example.yaroslavhorach.designsystem.theme.typoPrimary
import com.example.yaroslavhorach.pomo.R
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
            topBar = {
                if (appState.isBottomBarAndTopBarVisible) {
                    PomoTopBar()
                }
            },
            bottomBar = {
                if (appState.isBottomBarAndTopBarVisible) {
                    PomoBottomBar(
                        destinations = appState.topLevelDestinations,
                        onNavigateToDestination = appState::navigateToTopLevelDestination,
                        currentDestination = appState.currentDestination,
                        onNavigateToCreateTask = { appState.navigateToAddTask() }
                    )
                }
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
private fun PomoTopBar() {
    Row(
        modifier = Modifier
            .windowInsetsPadding(WindowInsets.statusBars)
            .padding(top = 20.dp, start = 20.dp)
    ) {
        Box(
            modifier = Modifier
                .background(MaterialTheme.colorScheme.primary, RoundedCornerShape(12.dp))
                .size(40.dp)
        )
        Text(
            modifier = Modifier
                .padding(start = 14.dp)
                .align(CenterVertically),
            text = stringResource(id = R.string.app_name),
            style = PomoTypography.h5,
            color = MaterialTheme.colorScheme.typoPrimary()
        )
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
            AddNavBarItems(destination, selected, onNavigateToDestination, onNavigateToCreateTask)
        }
    }
}

@Composable
private fun RowScope.AddNavBarItems(
    destination: TopLevelDestination,
    selected: Boolean,
    onNavigateToDestination: (TopLevelDestination) -> Unit,
    onNavigateToCreateTask: () -> Unit
) {
    if (destination is TopLevelDestination.Calendar) {
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

        AddTaskPomoNavigationBarItem { onNavigateToCreateTask() }
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

private fun NavDestination?.isTopLevelDestinationInHierarchy(destination: TopLevelDestination): Boolean {
    return this?.hierarchy?.any {
        it.route?.contains(destination.navigationRoute.toString(), true) ?: false
    } ?: false
}
