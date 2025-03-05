package com.example.yaroslavhorach.createTask

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.TimePicker
import androidx.compose.material3.rememberTimePickerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.BottomEnd
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.example.yaroslavhorach.CreateTask.R
import com.example.yaroslavhorach.createTask.model.CreateTaskAction
import com.example.yaroslavhorach.designsystem.theme.components.PrimaryTextButton
import java.util.Calendar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun TaskTimePicker(actioner: (CreateTaskAction) -> Unit, onDismissRequest: () -> Unit) {
    val calendar = Calendar.getInstance()
    val hour = calendar[Calendar.HOUR_OF_DAY]
    val minute = calendar[Calendar.MINUTE]

    val state = rememberTimePickerState(hour, minute, true)
    Dialog({
        onDismissRequest()
        actioner(CreateTaskAction.SelectTime("${state.hour}:${state.minute}"))
    }) {
        Box(
            modifier = Modifier
                .wrapContentSize()
                .background(MaterialTheme.colorScheme.background, RoundedCornerShape(16.dp))
        ) {
            TimePicker(modifier = Modifier.padding(32.dp), state = state)
            PrimaryTextButton(
                modifier = Modifier.align(BottomEnd),
                stringResource(id = R.string.create_task_time_picker_dialog_ok_btn_text)
            ) {
                onDismissRequest()
                actioner(CreateTaskAction.SelectTime("${state.hour}:${state.minute}"))
            }
        }
    }
}
