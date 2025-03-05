package com.example.yaroslavhorach.data.task.usecases

import com.example.yaroslavhorach.data.task.repository.TaskRepository
import com.example.yaroslavhorach.domain.task.models.Task
import com.example.yaroslavhorach.domain.task.usecases.GetTodayTasksUseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetTodayTasksUseCaseImpl @Inject constructor(
    private val taskRepository: TaskRepository
) : GetTodayTasksUseCase {

    override fun invoke(): Flow<List<Task>> {
        return taskRepository.getTodayTasks()
    }
}