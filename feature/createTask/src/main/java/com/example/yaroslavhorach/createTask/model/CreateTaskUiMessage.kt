package com.example.yaroslavhorach.createTask.model

sealed class CreateTaskUiMessage {
    object ShowBottomShit: CreateTaskUiMessage()
    object HideBottomShit: CreateTaskUiMessage()
    object ShowTimePicker: CreateTaskUiMessage()
    class ShowDatePicker(val selectedDate: Long?): CreateTaskUiMessage()
    class ShowRangePicker(val start: Long?, val end: Long?): CreateTaskUiMessage()
}