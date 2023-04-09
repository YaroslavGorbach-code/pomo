package com.example.yaroslavhorach.common.utill

import android.content.Context
import com.example.yaroslavhorach.common.R

fun Long.toToHoursMinutes(context: Context): String {
    val minutes = (this / (1000 * 60) % 60)
    val hours = (this / (1000 * 60 * 60) % 24)

    if (minutes > 0 && hours <= 0) {
        return context.resources.getQuantityString(
            R.plurals.plurals_minutes,
            minutes.toInt(),
            minutes.toInt()
        )
    }

    if (hours > 0 && minutes <= 0) {
        return context.resources.getQuantityString(
            R.plurals.plurals_hours,
            hours.toInt(),
            hours.toInt()
        )
    }

    if (hours > 0 && minutes > 0) {
        return context.resources.getQuantityString(
            R.plurals.plurals_hours,
            hours.toInt(),
            hours.toInt()
        ) + " " + context.resources.getQuantityString(
            R.plurals.plurals_minutes,
            minutes.toInt(),
            minutes.toInt()
        )
    }

    return ""
}