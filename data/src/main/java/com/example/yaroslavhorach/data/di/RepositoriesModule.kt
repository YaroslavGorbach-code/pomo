package com.example.yaroslavhorach.data.di

import com.example.yaroslavhorach.data.tasks.repository.TasksRepository
import com.example.yaroslavhorach.data.tasks.repository.TasksRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface RepositoriesModule {

    @Binds
    fun bindsTasksRepository(tasksRepository: TasksRepositoryImpl): TasksRepository
}
