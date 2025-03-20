package com.example.yaroslavhorach.database.di

import com.example.yaroslavhorach.database.PomoDatabase
import com.example.yaroslavhorach.database.dao.TagDao
import com.example.yaroslavhorach.database.dao.TaskDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal object DaosModule {

    @Provides
    fun providesTasksDao(database: PomoDatabase): TaskDao {
        return database.tasksDao()
    }

    @Provides
    fun providesTagsDao(database: PomoDatabase): TagDao {
        return database.tagDao()
    }
}
