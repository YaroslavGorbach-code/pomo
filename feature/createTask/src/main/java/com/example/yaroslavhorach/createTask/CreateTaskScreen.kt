package com.example.yaroslavhorach.createTask

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.yaroslavhorach.createTask.model.CreateTaskAction
import com.example.yaroslavhorach.createTask.model.CreateTaskViewState
import com.example.yaroslavhorach.designsystem.theme.PomoTheme

@Composable
internal fun CreateTaskRoute(
    viewModel: CreateTaskViewModel = hiltViewModel(),
) {
    val homeState by viewModel.state.collectAsStateWithLifecycle()

    CreateTaskScreen(
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
internal fun CreateTaskScreen(
    screenState: CreateTaskViewState,
    onMessageShown: (id: Long) -> Unit,
    actioner: (CreateTaskAction) -> Unit,
) {
}

@Preview
@Composable
private fun CreateTaskPreview() {
    PomoTheme { CreateTaskScreen(CreateTaskViewState.Preview, {}, {}) }
}

