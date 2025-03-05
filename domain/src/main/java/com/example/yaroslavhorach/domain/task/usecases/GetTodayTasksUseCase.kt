package com.example.yaroslavhorach.domain.task.usecases

import com.example.yaroslavhorach.domain.task.models.Task
import kotlinx.coroutines.flow.Flow

interface GetTodayTasksUseCase {
    operator fun invoke() : Flow<List<Task>>
}