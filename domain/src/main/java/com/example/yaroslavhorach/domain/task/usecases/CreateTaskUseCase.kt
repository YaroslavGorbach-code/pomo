package com.example.yaroslavhorach.domain.task.usecases

import com.example.yaroslavhorach.domain.task.models.Task

interface CreateTaskUseCase {
    suspend operator fun invoke(task: Task)
}