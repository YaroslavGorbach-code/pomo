package com.example.yaroslavhorach.pomo.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.example.yaroslavhorach.createTask.navigation.createTaskScreen
import com.example.yaroslavhorach.home.navigation.homeNavigationRoute
import com.example.yaroslavhorach.home.navigation.homeScreen

@Composable
fun PomoNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier,
    startDestination: String = homeNavigationRoute
) {
    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier,
    ) {
        homeScreen(onTaskClicked = {})
        createTaskScreen()
    }
}
