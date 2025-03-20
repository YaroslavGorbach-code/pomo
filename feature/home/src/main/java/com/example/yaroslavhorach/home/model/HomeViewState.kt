package com.example.yaroslavhorach.home.model

import com.example.yaroslavhorach.common.utill.UiMessage
import com.example.yaroslavhorach.designsystem.theme.TaskAndTagsBackgrounds
import com.example.yaroslavhorach.designsystem.theme.graphics.PomoIcons
import com.example.yaroslavhorach.domain.task.models.Task
import com.example.yaroslavhorach.home.R
import java.util.*

data class HomeViewState(
    val tasks: List<Task> = emptyList(),
    val userName: String = "",
    val uiMessage: UiMessage<HomeUiMessage>? = null
) {
    val completedAmount: Int = tasks.filter { it.isFinish }.size

    val dailyTasksProgress: Float
        get() {
            if (tasks.size.toFloat() == 0f) return 0f
            return completedAmount.toFloat() / tasks.size.toFloat()
        }

    val areAllTasksCompleted: Boolean
        get() {
            return dailyTasksProgress == 1.0f
        }

    val progressTitleRes: Int
        get() {
            return when {
                dailyTasksProgress < 0.5f -> R.string.home_daily_goals_not_done_text
                dailyTasksProgress >= 0.5f && dailyTasksProgress != 1.0f -> R.string.home_daily_goals_almost_done_text
                dailyTasksProgress == 1.0f -> R.string.home_daily_goals_done_text
                else -> throw Exception("Invalid progress")
            }
        }

    companion object {
        val Empty = HomeViewState()
        val Preview = HomeViewState(
            listOf(
                Task(
                    1,
                    "test",
                    600000,
                    false,
                    Date(),
                    TaskAndTagsBackgrounds.first().value.toLong(),
                    PomoIcons.TaskCategoriesIcons.first(),
                    Task.Priority.HIGH
                )
            )
        )
    }
}
