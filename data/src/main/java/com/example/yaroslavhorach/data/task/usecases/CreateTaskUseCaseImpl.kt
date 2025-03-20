package com.example.yaroslavhorach.data.task.usecases

import com.example.yaroslavhorach.domain.task.TaskRepository
import com.example.yaroslavhorach.domain.task.models.Task
import com.example.yaroslavhorach.domain.task.usecases.CreateTaskUseCase
import javax.inject.Inject

class CreateTaskUseCaseImpl @Inject constructor(
    private val taskRepository: TaskRepository
) : CreateTaskUseCase {

    override suspend fun invoke(task: Task) {
        return taskRepository.createTask(task)
    }
}