package com.example.yaroslavhorach.domain.models

import java.util.Date

data class Task(val id: Long, val name: String, val duration: Long, val isFinish: Boolean, val date: Date)