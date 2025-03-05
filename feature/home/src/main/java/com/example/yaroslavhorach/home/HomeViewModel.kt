package com.example.yaroslavhorach.home

import androidx.lifecycle.viewModelScope
import com.example.yaroslavhorach.common.base.BaseViewModel
import com.example.yaroslavhorach.domain.task.usecases.GetTodayTasksUseCase
import com.example.yaroslavhorach.home.model.HomeAction
import com.example.yaroslavhorach.home.model.HomeUiMessage
import com.example.yaroslavhorach.home.model.HomeViewState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    getTodayTasksUseCase: GetTodayTasksUseCase
) : BaseViewModel<HomeViewState, HomeAction, HomeUiMessage>() {

    override val pendingActions: MutableSharedFlow<HomeAction> = MutableSharedFlow()

    override val state: StateFlow<HomeViewState> = combine(getTodayTasksUseCase(), uiMessageManager.message) { tasks, messages->
        HomeViewState(tasks = tasks, uiMessage = messages)
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = HomeViewState.Empty
    )
}
