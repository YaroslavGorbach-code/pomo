package com.example.yaroslavhorach.createTask.model

import com.example.yaroslavhorach.common.utill.UiMessage

data class CreateTaskViewState(
    val uiMessage: UiMessage<CreateTaskUiMessage>? = null
) {

    companion object {
        val Empty = CreateTaskViewState()
        val Preview = CreateTaskViewState()
    }
}
