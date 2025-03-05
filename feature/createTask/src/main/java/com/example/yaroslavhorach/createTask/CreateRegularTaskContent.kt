package com.example.yaroslavhorach.createTask

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.PlatformTextStyle
import androidx.compose.ui.unit.dp
import com.example.yaroslavhorach.CreateTask.R
import com.example.yaroslavhorach.common.utill.toReadableDate
import com.example.yaroslavhorach.createTask.model.CreateTaskAction
import com.example.yaroslavhorach.createTask.model.CreateTaskViewState
import com.example.yaroslavhorach.designsystem.theme.PomoTypography.h4
import com.example.yaroslavhorach.designsystem.theme.components.PomoPicker
import com.example.yaroslavhorach.designsystem.theme.divider
import com.example.yaroslavhorach.designsystem.theme.graphics.PomoIcons
import com.example.yaroslavhorach.designsystem.theme.primaryIcon

@Composable
internal fun CreateRegularTaskContent(
    state: CreateTaskViewState,
    actioner: (CreateTaskAction) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp)
            .navigationBarsPadding()
    ) {
        Text(
            modifier = Modifier
                .align(CenterHorizontally)
                .padding(vertical = 24.dp),
            text = stringResource(id = R.string.create_regular_task_dialog_title_text),
            style = h4.copy(platformStyle = PlatformTextStyle(includeFontPadding = false)),
            color = MaterialTheme.colorScheme.primary
        )
        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .height(1.dp)
                .padding(horizontal = 20.dp)
                .background(MaterialTheme.colorScheme.divider())
        )
        PomoPicker(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 18.dp),
            title = stringResource(id = R.string.create_regular_task_dialog_period_field_title_text),
            hint = stringResource(id = R.string.create_regular_task_dialog_period_field_hind_text),
            value = state.newRegularTaskDateRange?.start?.toReadableDate() + "-" + state.newRegularTaskDateRange?.endInclusive?.toReadableDate(),
            onClick = { actioner(CreateTaskAction.PeriodPickerClick) },
            iconRes = PomoIcons.Calendar,
            iconTint = MaterialTheme.colorScheme.primaryIcon()
        )
    }
}
