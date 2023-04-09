package com.example.yaroslavhorach.data.tasks.repository

import androidx.compose.ui.graphics.toArgb
import com.example.yaroslavhorach.designsystem.theme.TaskBackgrounds
import com.example.yaroslavhorach.designsystem.theme.graphics.PomoIcons
import com.example.yaroslavhorach.domain.models.Task
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import java.util.*
import javax.inject.Inject

class TasksRepositoryImpl @Inject constructor() : TasksRepository {

    override fun getTodayTasks(): Flow<List<Task>> {
        return flowOf(
            listOf(
                Task(
                    1,
                    "Test 1",
                    1000 * 60 * 10,
                    false,
                    Date(),
                    TaskBackgrounds.first().toArgb().toLong(),
                    PomoIcons.taskCategories[0]
                ),
                Task(
                    1,
                    "Test 2",
                    1000 * 60 * 60 + 1000 * 60 * 30,
                    false,
                    Date(),
                    TaskBackgrounds[1].toArgb().toLong(),
                    PomoIcons.taskCategories[1]
                ),
                Task(
                    1,
                    "Test 3",
                    1000 * 60 * 15,
                    false,
                    Date(),
                    TaskBackgrounds[2].toArgb().toLong(),
                    PomoIcons.taskCategories[2]
                ),
                Task(
                    1,
                    "Test 4",
                    1000 * 60 * 30,
                    false,
                    Date(),
                    TaskBackgrounds[3].toArgb().toLong(),
                    PomoIcons.taskCategories[3]
                )
            )
        )
    }
}