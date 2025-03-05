package com.example.yaroslavhorach.createTask.model

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import com.example.yaroslavhorach.common.utill.UiMessage
import com.example.yaroslavhorach.designsystem.theme.TaskBackgrounds
import kotlin.math.roundToLong

data class CreateTaskViewState(
    val newTaskTitle: String? = null,
    val newTaskIconRes: Int? = null,
    val newTaskDurationSliderValue: Float = MIN_TASK_DURATION_MINUTES,
    val newTaskWorkingSessionsValue: Float = MIN_WORKING_SESSIONS,
    val newTaskBreakTimeValue: Float = MIN_BREAK_TIME_MINUTES,
    val newTaskColor: Color = DEFAULT_TASK_COLOR,
    val newTaskDate: Long? = null,
    val newTaskTime: String? = null,
    val newRegularTaskDateRange: LongRange? = null,
    val bottomShitContent: BottomShit? = null,
    val uiMessage: UiMessage<CreateTaskUiMessage>? = null
) {
    val taskDurationMs: Long = newTaskDurationSliderValue.roundToLong().times(60000)

    val taskBreakTimeMs: Long = newTaskBreakTimeValue.roundToLong().times(60000)

    sealed class BottomShit {
        object RegularTask : BottomShit()
        class ColorPicker(val colors: List<Int>) : BottomShit()
        class IconPicker(val icons: List<Int>) : BottomShit()
    }

    companion object {
        val Empty = CreateTaskViewState()
        val Preview = CreateTaskViewState()

        val DEFAULT_TASK_COLOR = TaskBackgrounds.first()

        const val MIN_TASK_DURATION_MINUTES = 15f
        const val MAX_TASK_DURATION_MINUTES = 480f
        const val STEP_TASK_DURATION_MINUTES = 30f

        const val MIN_WORKING_SESSIONS = 1f
        const val MAX_WORKING_SESSIONS = 10f
        const val STEP_WORKING_SESSIONS = 8f

        const val MIN_BREAK_TIME_MINUTES = 5f
        const val MAX_BREAK_TIME_MINUTES = 60f
        const val STEP_BREAK_TIME_MINUTES = 10f
    }
}
