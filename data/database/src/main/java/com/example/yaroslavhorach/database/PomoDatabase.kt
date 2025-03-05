package com.example.yaroslavhorach.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.yaroslavhorach.database.dao.TaskDao
import com.example.yaroslavhorach.database.task.model.TaskEntity

@Database(
    entities = [TaskEntity::class],
    version = 1,
    exportSchema = true,
)

internal abstract class PomoDatabase : RoomDatabase() {
    abstract fun tasksDao(): TaskDao
}