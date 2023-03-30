package com.example.yaroslavhorach.data.tasks.usecases

import com.example.yaroslavhorach.data.tasks.repository.TasksRepository
import com.example.yaroslavhorach.domain.models.Task
import com.example.yaroslavhorach.domain.usecases.GetTodayTasksUseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetTodayTasksUseCaseImpl @Inject constructor(
    private val tasksRepository: TasksRepository
) : GetTodayTasksUseCase {

    override fun invoke(): Flow<List<Task>> {
        return tasksRepository.getTodayTasks()
    }
}