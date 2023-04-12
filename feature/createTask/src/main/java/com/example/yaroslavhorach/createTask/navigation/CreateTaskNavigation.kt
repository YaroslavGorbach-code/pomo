package com.example.yaroslavhorach.createTask.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.example.yaroslavhorach.createTask.CreateTaskRoute

const val createTaskNavigationRoute = "create_task_route"

fun NavController.navigateToCreateTask(navOptions: NavOptions? = null) {
    this.navigate(createTaskNavigationRoute, navOptions)
}

fun NavGraphBuilder.createTaskScreen() {
    composable(route = createTaskNavigationRoute) {
        CreateTaskRoute()
    }
}