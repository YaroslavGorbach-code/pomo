package com.example.yaroslavhorach.data.tasks.repository

import com.example.yaroslavhorach.domain.models.Task
import kotlinx.coroutines.flow.Flow

interface TasksRepository {
    fun getTodayTasks(): Flow<List<Task>>
}