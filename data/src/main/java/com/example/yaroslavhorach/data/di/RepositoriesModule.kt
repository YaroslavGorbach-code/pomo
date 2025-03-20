package com.example.yaroslavhorach.data.di

import com.example.yaroslavhorach.data.tag.repositiry.TagRepositoryImpl
import com.example.yaroslavhorach.domain.task.TaskRepository
import com.example.yaroslavhorach.data.task.repository.TaskRepositoryImpl
import com.example.yaroslavhorach.domain.tag.TagRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface RepositoriesModule {

    @Binds
    fun bindsTasksRepository(tasksRepository: TaskRepositoryImpl): TaskRepository

    @Binds
    fun bindsTagsRepository(tagsRepository: TagRepositoryImpl): TagRepository
}
