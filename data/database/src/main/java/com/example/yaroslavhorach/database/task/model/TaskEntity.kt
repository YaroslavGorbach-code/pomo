package com.example.yaroslavhorach.database.task.model

import androidx.room.PrimaryKey
import com.example.yaroslavhorach.domain.models.Task
import java.util.*

data class TaskEntity(
    @PrimaryKey
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
