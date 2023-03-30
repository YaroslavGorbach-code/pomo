package com.example.yaroslavhorach.home.model

import com.example.yaroslavhorach.common.utill.UiMessage
import com.example.yaroslavhorach.domain.models.Task
import java.util.*

data class HomeViewState(
    val tasks: List<Task> = emptyList(),
    val uiMessage: UiMessage<HomeUiMessage>? = null
) {
    companion object {
        val Empty = HomeViewState()
        val Preview = HomeViewState(listOf(Task(1, "test", 600000, false, Date())))
    }
}
