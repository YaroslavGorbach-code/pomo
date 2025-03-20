package com.example.yaroslavhorach.createTask.model

import androidx.compose.ui.graphics.Color
import com.example.yaroslavhorach.domain.tag.models.Tag
import com.example.yaroslavhorach.domain.task.models.Task

sealed class CreateTaskAction {
    data object DatePickerClicked : CreateTaskAction()
    data object PeriodPickerClicked : CreateTaskAction()
    class TaskDurationSliderValueChanged(val value: Float) : CreateTaskAction()
    data object CreateTaskClicked : CreateTaskAction()
    data object CreateTagClicked : CreateTaskAction()
    data object AddTagClicked : CreateTaskAction()
    class TagSelected(val tag: Tag) : CreateTaskAction()
    data object SelectTagPrimaryBtnClicked: CreateTaskAction()
    data object SelectTagSecondaryBtnClicked: CreateTaskAction()
    data object CreateTagSecondaryBtnClicked: CreateTaskAction()
    class DeleteTag(val id: Long): CreateTaskAction()
    class CreatePrimaryBtnClicked(val tagName: String, val color: Color): CreateTaskAction()
    data object CreateRegularTaskClicked : CreateTaskAction()
    class TaskColorChosen(val color: Color) : CreateTaskAction()
    class DateSelected(val date: Long) : CreateTaskAction()
    class PrioritySelected(val priority: Task.Priority) : CreateTaskAction()
    class DtaRangeSelected(val startDate: Long, val endDate: Long) : CreateTaskAction()
}