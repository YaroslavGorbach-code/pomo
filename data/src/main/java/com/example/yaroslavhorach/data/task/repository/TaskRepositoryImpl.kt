package com.example.yaroslavhorach.data.task.repository

import androidx.compose.ui.graphics.toArgb
import com.example.yaroslavhorach.database.dao.TaskDao
import com.example.yaroslavhorach.database.task.model.asEntityModel
import com.example.yaroslavhorach.designsystem.theme.TaskAndTagsBackgrounds
import com.example.yaroslavhorach.designsystem.theme.graphics.PomoIcons
import com.example.yaroslavhorach.domain.task.TaskRepository
import com.example.yaroslavhorach.domain.task.models.Task
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import java.util.*
import javax.inject.Inject

class TaskRepositoryImpl @Inject constructor(
    private val tasksDao: TaskDao
) : TaskRepository {

    override suspend fun createTask(task: Task) {
        tasksDao.upsertTasks(listOf(task.asEntityModel()))
    }

    override fun getTodayTasks(): Flow<List<Task>> {
        return flowOf(
            listOf(
                Task(
                    1,
                    "Test 1",
                    1000 * 60 * 10,
                    false,
                    Date(),
                    TaskAndTagsBackgrounds.first().toArgb().toLong(),
                    PomoIcons.TaskCategoriesIcons[0],
                    Task.Priority.HIGH
                ),
                Task(
                    1,
                    "Test 2",
                    1000 * 60 * 60 + 1000 * 60 * 30,
                    false,
                    Date(),
                    TaskAndTagsBackgrounds[1].toArgb().toLong(),
                    PomoIcons.TaskCategoriesIcons[1],
                    Task.Priority.HIGH
                ),
                Task(
                    1,
                    "Test 3",
                    1000 * 60 * 15,
                    false,
                    Date(),
                    TaskAndTagsBackgrounds[2].toArgb().toLong(),
                    PomoIcons.TaskCategoriesIcons[2],
                    Task.Priority.HIGH
                ),
                Task(
                    1,
                    "Test 4",
                    1000 * 60 * 30,
                    false,
                    Date(),
                    TaskAndTagsBackgrounds[3].toArgb().toLong(),
                    PomoIcons.TaskCategoriesIcons[3],
                    Task.Priority.HIGH
                )
            )
        )
    }
}