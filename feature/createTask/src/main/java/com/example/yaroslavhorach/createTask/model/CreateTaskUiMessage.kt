package com.example.yaroslavhorach.createTask.model

sealed class CreateTaskUiMessage {
    data object ShowBottomShit: CreateTaskUiMessage()
    data object HideBottomShit: CreateTaskUiMessage()
    class ShowDatePicker(val selectedDate: Long?): CreateTaskUiMessage()
    class ShowRangePicker(val start: Long?, val end: Long?): CreateTaskUiMessage()
}