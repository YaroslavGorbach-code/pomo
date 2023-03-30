package com.example.yaroslavhorach.domain.usecases

import com.example.yaroslavhorach.domain.models.Task
import kotlinx.coroutines.flow.Flow

interface GetTodayTasksUseCase {
    operator fun invoke() : Flow<List<Task>>
}