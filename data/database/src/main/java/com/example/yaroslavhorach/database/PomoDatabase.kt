package com.example.yaroslavhorach.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.yaroslavhorach.database.dao.TagDao
import com.example.yaroslavhorach.database.dao.TaskDao
import com.example.yaroslavhorach.database.task.model.TagEntity
import com.example.yaroslavhorach.database.task.model.TaskEntity

@Database(
    entities = [TaskEntity::class, TagEntity::class],
    version = 4,
    exportSchema = true,
)

internal abstract class PomoDatabase : RoomDatabase() {
    abstract fun tasksDao(): TaskDao
    abstract fun tagDao(): TagDao
}