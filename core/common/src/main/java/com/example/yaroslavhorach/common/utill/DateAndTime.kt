package com.example.yaroslavhorach.common.utill

import android.content.Context
import android.util.Log
import com.example.yaroslavhorach.common.R
import java.text.SimpleDateFormat
import java.util.*

fun Long.timeToToHoursMinutes(context: Context): String {
    Log.v("dsadas", this.toString())
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

fun Long.toReadableDate(): String{
    val date = Date(this)
    val format = SimpleDateFormat("dd.MM.yyyy", Locale.getDefault())
    return format.format(date)
}