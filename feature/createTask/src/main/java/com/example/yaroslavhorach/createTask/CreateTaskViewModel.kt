package com.example.yaroslavhorach.createTask

import androidx.lifecycle.viewModelScope
import com.example.yaroslavhorach.common.base.BaseViewModel
import com.example.yaroslavhorach.createTask.model.CreateTaskAction
import com.example.yaroslavhorach.createTask.model.CreateTaskUiMessage
import com.example.yaroslavhorach.createTask.model.CreateTaskViewState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import javax.inject.Inject

@HiltViewModel
class CreateTaskViewModel @Inject constructor() :
    BaseViewModel<CreateTaskViewState, CreateTaskAction, CreateTaskUiMessage>() {

    override val pendingActions: MutableSharedFlow<CreateTaskAction> = MutableSharedFlow()

    //todo remove flowOf("")
    override val state: StateFlow<CreateTaskViewState> =
        combine(flowOf(""), uiMessageManager.message) { _, messages ->
            CreateTaskViewState(uiMessage = messages)
        }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = CreateTaskViewState.Empty
        )
}
