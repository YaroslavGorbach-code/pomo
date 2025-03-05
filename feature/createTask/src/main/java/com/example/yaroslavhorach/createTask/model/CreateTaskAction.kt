package com.example.yaroslavhorach.createTask.model

sealed class CreateTaskAction {
    class TypeTitle(val text: String) : CreateTaskAction()
    object DataPickerClick : CreateTaskAction()
    object TimePickerClick : CreateTaskAction()
    object ColorPickerClick : CreateTaskAction()
    object IconPickerClick : CreateTaskAction()
    object PeriodPickerClick : CreateTaskAction()
    class TaskDurationSliderValueChange(val value: Float) : CreateTaskAction()
    class TaskWorkingSessionSliderValueChange(val value: Float) : CreateTaskAction()
    class TaskBreakTimeSliderValueChange(val value: Float) : CreateTaskAction()
    object CreateTaskClick : CreateTaskAction()
    object CreateRegularTaskClick : CreateTaskAction()
    class ChoseColor(val color: Int): CreateTaskAction()
    class ChoseIcon(val iconRes: Int): CreateTaskAction()
    class SelectDate(val date: Long): CreateTaskAction()
    class SelectTime(val time: String): CreateTaskAction()
    class SelectDateRange(val startDate: Long, val endDate: Long): CreateTaskAction()
}