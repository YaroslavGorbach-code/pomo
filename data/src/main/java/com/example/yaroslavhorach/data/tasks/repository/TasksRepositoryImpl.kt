package com.example.yaroslavhorach.data.tasks.repository

import com.example.yaroslavhorach.domain.models.Task
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import java.util.*
import javax.inject.Inject

class TasksRepositoryImpl @Inject constructor() : TasksRepository {

    override fun getTodayTasks(): Flow<List<Task>> {
        return flowOf(
            listOf(
                Task(1, "Test 1", 1000 * 60, false, Date()),
                Task(1, "Test 2", 1000 * 60, false, Date()),
                Task(1, "Test 3", 1000 * 60, false, Date()),
                Task(1, "Test 4", 1000 * 60, false, Date())
            )
        )
    }
}