package com.example.yaroslavhorach.database.task.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.yaroslavhorach.domain.task.models.Task
import java.util.*

@Entity(tableName = "tasks")
data class TaskEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long,
    val name: String,
    val duration: Long,
    val isFinish: Boolean,
    val date: Long,
    val color: Long,
    val iconId: Int
)

fun TaskEntity.asDomainModel() = Task(
    id = id,
    name = name,
    duration = duration,
    isFinish = isFinish,
    date = Date(date),
    color = color,
    iconId = iconId
)

fun Task.asEntityModel() = TaskEntity(
    id = id,
    name = name,
    duration = duration,
    isFinish = isFinish,
    date = date.time,
    color = color,
    iconId = iconId
)
