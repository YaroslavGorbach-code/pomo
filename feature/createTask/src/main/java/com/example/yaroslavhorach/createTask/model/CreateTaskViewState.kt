package com.example.yaroslavhorach.createTask.model

import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.graphics.Color
import com.example.yaroslavhorach.common.utill.TextValidation
import com.example.yaroslavhorach.common.utill.UiMessage
import com.example.yaroslavhorach.common.utill.Validation
import com.example.yaroslavhorach.designsystem.theme.components.input_fields.TextFieldState
import com.example.yaroslavhorach.domain.tag.models.Tag
import com.example.yaroslavhorach.domain.task.models.Task
import com.example.yaroslavhorach.ui.UiText
import kotlin.math.roundToLong

data class CreateTaskViewState(
    val taskTitle: TextFieldState = TextFieldState(
        validation = mutableStateOf(TextValidation(rules = arrayOf(Validation.Rule.RequiredField to UiText.Empty)))
    ),
    val taskIconRes: Int? = null,
    val taskPomodorosSliderValue: Float = MIN_TASK_POMODOUS,
    val colorPickerColors: List<Color> = emptyList(),
    val selectedColor: Color? = null,
    val selectedDate: Long? = null,
    val availableTags: List<Tag> = emptyList(),
    val selectedTags: Set<Tag> = emptySet(),
    val selectedPriority: Task.Priority = Task.Priority.LOW,
    val regularTaskDateRange: LongRange? = null,
    val bottomShitContent: BottomShit? = null,
    val uiMessage: UiMessage<CreateTaskUiMessage>? = null
) {
    val taskDurationMs: Long = (taskPomodorosSliderValue * POMODORO_IN_MINUTES).roundToLong().times(60000)

//    val newTask: Task?
//        get() {
//            return Task(name = )
//        }

    sealed class BottomShit {
        data object RegularTask : BottomShit()
        data object SelectTag : BottomShit()
        data object CreateTag : BottomShit()
    }

    companion object {
        val Empty = CreateTaskViewState()
        val Preview = CreateTaskViewState()

        const val TITLE_MAX_LENGTH = 50
        const val TAG_MAX_LENGTH = 20
        const val MIN_TASK_POMODOUS = 1f
        const val MAX_TASK_POMODOUS = 20f
        const val POMODOUS_SLIDER_STEPS = (MAX_TASK_POMODOUS - 2).toInt()
        const val POMODORO_IN_MINUTES = 25f
    }
}
