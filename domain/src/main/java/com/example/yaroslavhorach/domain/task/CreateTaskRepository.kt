package com.example.yaroslavhorach.domain.task

import com.example.yaroslavhorach.domain.task.models.Task


interface CreateTaskRepository {
    suspend fun createTask(task: Task)
}