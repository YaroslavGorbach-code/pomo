package com.example.yaroslavhorach.database.di

import com.example.yaroslavhorach.database.PomoDatabase
import com.example.yaroslavhorach.database.dao.TaskDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal object DaosModule {

    @Provides
    fun providesTasksDao(database: PomoDatabase): TaskDao = database.tasksDao()
}
