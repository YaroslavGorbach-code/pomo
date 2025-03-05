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
import java.util.Date

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun TaskDatePicker(
    initialDate: Long?,
    actioner: (CreateTaskAction) -> Unit,
    onDismissRequest: UseCaseState.() -> Unit
) {
//    val calendar = Calendar.getInstance()
//    val state = rememberDatePickerState(
//        initialSelectedDateMillis = calendar.timeInMillis,
//        selectableDates = object : SelectableDates {
//            override fun isSelectableDate(utcTimeMillis: Long): Boolean {
//                return DateUtils.isToday(utcTimeMillis) || utcTimeMillis > calendar.timeInMillis
//            }
//        })
//
//    Dialog({
//        onDismissRequest()
//        actioner(CreateTaskAction.SelectDate(state.selectedDateMillis ?: return@Dialog))
//    }) {
//        Box(
//            modifier = Modifier
//                .fillMaxWidth()
//                .background(MaterialTheme.colorScheme.background, RoundedCornerShape(16.dp))
//        ) {
//            androidx.compose.material3.DatePicker(modifier = Modifier, state = state)
//            PrimaryTextButton(
//                modifier = Modifier.align(BottomEnd),
//                stringResource(id = R.string.create_task_date_picker_dialog_ok_btn_text)
//            ) {
//                onDismissRequest()
//                actioner(
//                    CreateTaskAction.SelectDate(
//                        state.selectedDateMillis ?: return@PrimaryTextButton
//                    )
//                )
//            }
//        }
//    }


    CalendarDialog(
        state = rememberUseCaseState(visible = true, true, onCloseRequest = onDismissRequest),
        config = CalendarConfig(
            yearSelection = true,
            monthSelection = true,
            style = CalendarStyle.MONTH,
            boundary = LocalDate.now()..LocalDate.now().plusYears(20L)
        ),
        selection = CalendarSelection.Date(
            selectedDate = Instant.ofEpochMilli(initialDate?: Date().time )
                .atZone(ZoneId.systemDefault())
                .toLocalDate()
        ) { selected ->
            actioner(
                CreateTaskAction.SelectDate(
                    selected.atStartOfDay().toInstant(ZoneOffset.UTC).toEpochMilli()
                )
            )
        },
    )
}
