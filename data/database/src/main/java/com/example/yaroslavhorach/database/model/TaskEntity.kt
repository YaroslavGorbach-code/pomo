package com.example.yaroslavhorach.database.model

import androidx.room.PrimaryKey
import com.example.yaroslavhorach.domain.models.Task
import java.util.Date

data class TaskEntity(
    @PrimaryKey
    val id: Long,
    val name: String,
    val duration: Long,
    val isFinish: Boolean,
    val date: Date
)

fun TaskEntity.asDomainModel() = Task(
    id = id,
    name = name,
    duration = duration,
    isFinish = isFinish,
    date = date
)
