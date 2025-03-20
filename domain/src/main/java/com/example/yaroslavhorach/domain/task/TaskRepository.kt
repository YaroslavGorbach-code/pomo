package com.example.yaroslavhorach.domain.task

import com.example.yaroslavhorach.domain.task.models.Task
import kotlinx.coroutines.flow.Flow

interface TaskRepository {
    fun getTodayTasks(): Flow<List<Task>>
    suspend fun createTask(task: Task)
}