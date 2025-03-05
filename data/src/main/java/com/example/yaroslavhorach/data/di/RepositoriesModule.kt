package com.example.yaroslavhorach.data.di

import com.example.yaroslavhorach.data.task.repository.TaskRepository
import com.example.yaroslavhorach.data.task.repository.TaskRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface RepositoriesModule {

    @Binds
    fun bindsTasksRepository(tasksRepository: TaskRepositoryImpl): TaskRepository
}
