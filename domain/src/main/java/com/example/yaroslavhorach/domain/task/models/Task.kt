package com.example.yaroslavhorach.domain.task.models

import java.util.Date

data class Task(
    val id: Long = 0,
    val name: String,
    val duration: Long,
    val isFinish: Boolean,
    val date: Date,
    val color: Long,
    val iconId: Int
)