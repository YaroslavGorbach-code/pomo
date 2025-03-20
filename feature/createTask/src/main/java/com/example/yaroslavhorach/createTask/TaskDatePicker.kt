package com.example.yaroslavhorach.createTask

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
import java.util.Date

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun TaskDatePicker(
    initialDate: Long?,
    actioner: (CreateTaskAction) -> Unit,
    onDismissRequest: UseCaseState.() -> Unit
) {
    CalendarDialog(
        state = rememberUseCaseState(visible = true, true, onCloseRequest = onDismissRequest),
        config = CalendarConfig(
            yearSelection = true,
            monthSelection = true,
            style = CalendarStyle.MONTH,
            boundary = LocalDate.now()..LocalDate.now().plusYears(20L)
        ),
        selection = CalendarSelection.Date(
            selectedDate = Instant.ofEpochMilli(initialDate ?: Date().time)
                .atZone(ZoneId.systemDefault())
                .toLocalDate()
        ) { selected ->
            actioner(
                CreateTaskAction.DateSelected(
                    selected.atStartOfDay().toInstant(ZoneOffset.UTC).toEpochMilli()
                )
            )
        },
    )
}
