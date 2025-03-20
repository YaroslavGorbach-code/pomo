package com.example.yaroslavhorach.createTask

import android.util.Range
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import com.example.yaroslavhorach.createTask.model.CreateTaskAction
import com.maxkeppeker.sheets.core.models.base.UseCaseState
import com.maxkeppeker.sheets.core.models.base.rememberUseCaseState
import com.maxkeppeler.sheets.calendar.CalendarDialog
import com.maxkeppeler.sheets.calendar.models.CalendarConfig
import com.maxkeppeler.sheets.calendar.models.CalendarSelection
import com.maxkeppeler.sheets.calendar.models.CalendarStyle
import java.time.Instant
import java.time.LocalDate
import java.time.ZoneId
import java.time.ZoneOffset

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun DateRangePickerDialog(
    initialStart: Long?,
    initialEnd: Long?,
    actioner: (CreateTaskAction) -> Unit,
    onDismissRequest: UseCaseState.() -> Unit
) {
    val selectedRange = if (initialStart != null && initialEnd != null) {
        Range(
            Instant.ofEpochMilli(initialStart).atZone(ZoneId.systemDefault()).toLocalDate(),
            Instant.ofEpochMilli(initialEnd).atZone(ZoneId.systemDefault()).toLocalDate(),
        )
    } else {
        null
    }

    CalendarDialog(
        state = rememberUseCaseState(visible = true, true, onCloseRequest = onDismissRequest),
        config = CalendarConfig(
            yearSelection = true,
            monthSelection = true,
            style = CalendarStyle.MONTH,
            boundary = LocalDate.now()..LocalDate.now().plusYears(20L)
        ),
        selection = CalendarSelection.Period(selectedRange = selectedRange) { startDate, endDate ->
            actioner(
                CreateTaskAction.DtaRangeSelected(
                    startDate.atStartOfDay().toInstant(ZoneOffset.UTC).toEpochMilli(),
                    endDate.atStartOfDay().toInstant(ZoneOffset.UTC).toEpochMilli()
                )
            )
        },
    )
}