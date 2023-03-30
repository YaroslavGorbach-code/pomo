package com.example.yaroslavhorach.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.yaroslavhorach.designsystem.theme.PomoTheme
import com.example.yaroslavhorach.home.model.HomeAction
import com.example.yaroslavhorach.home.model.HomeViewState

@Composable
internal fun HomeRoute(
    onTaskClicked: (Long) -> Unit,
    viewModel: HomeViewModel = hiltViewModel(),
) {
    val homeState by viewModel.state.collectAsStateWithLifecycle()

    HomeScreen(
        screenState = homeState,
        onMessageShown = viewModel::clearMessage,
        actioner = { action ->
            when (action) {
                else -> viewModel.submitAction(action)
            }
        }
    )
}

@Composable
internal fun HomeScreen(
    screenState: HomeViewState,
    onMessageShown: (id: Long) -> Unit,
    actioner: (HomeAction) -> Unit,
) {
    Box(modifier = Modifier.fillMaxSize())
}

@Preview
@Composable
private fun BookmarksGridPreview() {
    PomoTheme { HomeScreen(HomeViewState.Preview, {}, {}) }
}

