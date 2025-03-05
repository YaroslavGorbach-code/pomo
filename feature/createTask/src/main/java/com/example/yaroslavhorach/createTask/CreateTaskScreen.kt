package com.example.yaroslavhorach.createTask

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetLayout
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.PlatformTextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.yaroslavhorach.CreateTask.R
import com.example.yaroslavhorach.common.utill.timeToToHoursMinutes
import com.example.yaroslavhorach.common.utill.toReadableDate
import com.example.yaroslavhorach.createTask.model.CreateTaskAction
import com.example.yaroslavhorach.createTask.model.CreateTaskUiMessage
import com.example.yaroslavhorach.createTask.model.CreateTaskViewState
import com.example.yaroslavhorach.createTask.model.CreateTaskViewState.Companion.MAX_BREAK_TIME_MINUTES
import com.example.yaroslavhorach.createTask.model.CreateTaskViewState.Companion.MAX_TASK_DURATION_MINUTES
import com.example.yaroslavhorach.createTask.model.CreateTaskViewState.Companion.MAX_WORKING_SESSIONS
import com.example.yaroslavhorach.createTask.model.CreateTaskViewState.Companion.MIN_BREAK_TIME_MINUTES
import com.example.yaroslavhorach.createTask.model.CreateTaskViewState.Companion.MIN_TASK_DURATION_MINUTES
import com.example.yaroslavhorach.createTask.model.CreateTaskViewState.Companion.MIN_WORKING_SESSIONS
import com.example.yaroslavhorach.createTask.model.CreateTaskViewState.Companion.STEP_BREAK_TIME_MINUTES
import com.example.yaroslavhorach.createTask.model.CreateTaskViewState.Companion.STEP_TASK_DURATION_MINUTES
import com.example.yaroslavhorach.createTask.model.CreateTaskViewState.Companion.STEP_WORKING_SESSIONS
import com.example.yaroslavhorach.designsystem.theme.PomoTheme
import com.example.yaroslavhorach.designsystem.theme.PomoTypography
import com.example.yaroslavhorach.designsystem.theme.PomoTypography.h4
import com.example.yaroslavhorach.designsystem.theme.components.PomoInputField
import com.example.yaroslavhorach.designsystem.theme.components.PomoPicker
import com.example.yaroslavhorach.designsystem.theme.components.PomoSlider
import com.example.yaroslavhorach.designsystem.theme.components.PrimaryButton
import com.example.yaroslavhorach.designsystem.theme.components.PrimaryVariantButton
import com.example.yaroslavhorach.designsystem.theme.divider
import com.example.yaroslavhorach.designsystem.theme.graphics.PomoIcons
import com.example.yaroslavhorach.designsystem.theme.primaryIcon
import com.example.yaroslavhorach.designsystem.theme.primaryVariant
import com.example.yaroslavhorach.designsystem.theme.typoPrimary

@Composable
internal fun CreateTaskRoute(
    viewModel: CreateTaskViewModel = hiltViewModel(),
) {
    val homeState by viewModel.state.collectAsStateWithLifecycle()

    CreateTaskScreen(screenState = homeState,
        onMessageShown = viewModel::clearMessage,
        actioner = { action ->
            when (action) {
                else -> viewModel.submitAction(action)
            }
        })
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
internal fun CreateTaskScreen(
    screenState: CreateTaskViewState,
    onMessageShown: (id: Long) -> Unit,
    actioner: (CreateTaskAction) -> Unit,
) {
    val scrollableState = rememberScrollState()
    val modalSheetState = rememberModalBottomSheetState(
        initialValue = ModalBottomSheetValue.Hidden,
        confirmValueChange = { it != ModalBottomSheetValue.HalfExpanded },
    )

    when (val message = screenState.uiMessage?.message) {
        is CreateTaskUiMessage.ShowBottomShit -> {
            LaunchedEffect(key1 = screenState.uiMessage.id, block = { modalSheetState.show() })
            onMessageShown(screenState.uiMessage.id)
        }
        is CreateTaskUiMessage.HideBottomShit -> {
            LaunchedEffect(key1 = screenState.uiMessage.id, block = { modalSheetState.hide() })
            onMessageShown(screenState.uiMessage.id)
        }
        is CreateTaskUiMessage.ShowTimePicker -> {
            TaskTimePicker(actioner) { onMessageShown(screenState.uiMessage.id) }
        }
        is CreateTaskUiMessage.ShowDatePicker -> {
            TaskDatePicker(message.selectedDate, actioner) { onMessageShown(screenState.uiMessage.id) }
        }
        is CreateTaskUiMessage.ShowRangePicker -> {
            DateRangePickerDialog(
                initialStart = screenState.newRegularTaskDateRange?.start,
                initialEnd = screenState.newRegularTaskDateRange?.endInclusive,
                actioner
            ) { onMessageShown(screenState.uiMessage.id) }
        }
        null -> {}
    }

    ModalBottomSheetLayout(sheetShape = RoundedCornerShape(16.dp),
        sheetBackgroundColor = MaterialTheme.colorScheme.background,
        sheetState = modalSheetState,
        sheetContent = {
            when (screenState.bottomShitContent) {
                is CreateTaskViewState.BottomShit.RegularTask -> CreateRegularTaskContent(
                    screenState, actioner
                )
                is CreateTaskViewState.BottomShit.ColorPicker -> ColorPickerBottomShitContent(
                    screenState.bottomShitContent.colors, actioner
                )
                is CreateTaskViewState.BottomShit.IconPicker -> IconPickerBottomShitContent(
                    screenState.bottomShitContent.icons, actioner
                )
                null -> {}
            }
        }) { ScreenContent(scrollableState, screenState, actioner) }
}

@Composable
private fun ScreenContent(
    scrollableState: ScrollState,
    screenState: CreateTaskViewState,
    actioner: (CreateTaskAction) -> Unit
) {
    Column(
        modifier = Modifier
            .padding(horizontal = 20.dp)
            .windowInsetsPadding(WindowInsets.statusBars)
            .windowInsetsPadding(WindowInsets.navigationBars)
            .imePadding()
            .fillMaxSize()
            .verticalScroll(scrollableState)
    ) {
        Toolbar()
        Spacer(modifier = Modifier.height(24.dp))
        TitleTextField(screenState, actioner)
        Spacer(modifier = Modifier.height(20.dp))
        Row {
            DatePicker(screenState, actioner)
            Spacer(modifier = Modifier.width(20.dp))
            TaskTimePicker(screenState, actioner)
        }
        Spacer(modifier = Modifier.height(20.dp))
        Row {
            ColorPicker(screenState, actioner)
            Spacer(modifier = Modifier.width(20.dp))
            IconPicker(screenState, actioner)
        }
        Spacer(modifier = Modifier.height(20.dp))
        TaskDurationSlider(screenState, actioner)
        Spacer(modifier = Modifier.height(20.dp))
        WorkingSessionsSlider(screenState, actioner)
        Spacer(modifier = Modifier.height(20.dp))
        BreakTimeSlider(screenState, actioner)
        Spacer(modifier = Modifier.weight(1f))
        PrimaryButton(
            modifier = Modifier.fillMaxWidth(),
            text = stringResource(id = R.string.create_task_create_task_btn_text)
        ) { actioner(CreateTaskAction.CreateTaskClick) }
        Spacer(modifier = Modifier.height(20.dp))
        PrimaryVariantButton(
            modifier = Modifier.fillMaxWidth(),
            text = stringResource(id = R.string.create_task_create_regular_task_btn_text)
        ) { actioner(CreateTaskAction.CreateRegularTaskClick) }
        Spacer(modifier = Modifier.height(20.dp))
    }
}

@Composable
private fun Toolbar() {
    Row(modifier = Modifier.padding(top = 24.dp)) {
        Icon(
            modifier = Modifier.padding(end = 16.dp),
            painter = painterResource(PomoIcons.ArrowLeft),
            contentDescription = null,
            tint = MaterialTheme.colorScheme.typoPrimary()
        )
        Text(
            text = stringResource(id = R.string.create_task_toolbar_title_text),
            style = PomoTypography.h5,
            color = MaterialTheme.colorScheme.typoPrimary()
        )
    }
}

@Composable
private fun TitleTextField(state: CreateTaskViewState, actioner: (CreateTaskAction) -> Unit) {
    PomoInputField(modifier = Modifier.fillMaxWidth(),
        title = stringResource(id = R.string.create_task_text_field_title_title_text),
        hint = stringResource(id = R.string.create_task_text_field_hint_title_text),
        value = state.newTaskTitle ?: "",
        onValueChange = { actioner(CreateTaskAction.TypeTitle(it)) })
}

@Composable
private fun RowScope.DatePicker(state: CreateTaskViewState, actioner: (CreateTaskAction) -> Unit) {
    PomoPicker(
        value = state.newTaskDate?.toReadableDate(),
        modifier = Modifier.weight(1f),
        title = stringResource(id = R.string.create_task_data_picker_title_text),
        hint = stringResource(id = R.string.create_task_data_picker_hint_text),
        iconRes = PomoIcons.Calendar,
        iconTint = MaterialTheme.colorScheme.primaryIcon()
    ) {
        actioner(CreateTaskAction.DataPickerClick)
    }
}

@Composable
private fun RowScope.TaskTimePicker(state: CreateTaskViewState, actioner: (CreateTaskAction) -> Unit) {
    PomoPicker(
        modifier = Modifier.weight(1f),
        value = state.newTaskTime,
        title = stringResource(id = R.string.create_task_time_picker_title_text),
        hint = stringResource(id = R.string.create_task_time_picker_hint_text),
        iconRes = PomoIcons.ClockFive,
        iconTint = MaterialTheme.colorScheme.primaryIcon()
    ) {
        actioner(CreateTaskAction.TimePickerClick)
    }
}

@Composable
private fun RowScope.ColorPicker(state: CreateTaskViewState, actioner: (CreateTaskAction) -> Unit) {
    Column(modifier = Modifier.weight(1f)) {
        Text(
            text = stringResource(id = R.string.create_task_color_picker_title_text),
            style = PomoTypography.body2,
            color = MaterialTheme.colorScheme.typoPrimary()
        )
        Box(modifier = Modifier
            .size(50.dp)
            .padding(top = 4.dp)
            .background(MaterialTheme.colorScheme.onBackground, RoundedCornerShape(6.dp))
            .clickable { actioner(CreateTaskAction.ColorPickerClick) }) {
            Spacer(
                modifier = Modifier
                    .size(32.dp)
                    .background(state.newTaskColor, RoundedCornerShape(4.dp))
                    .align(Center)
            )
        }
    }
}

@Composable
private fun RowScope.IconPicker(state: CreateTaskViewState, actioner: (CreateTaskAction) -> Unit) {
    Column(modifier = Modifier.weight(1f)) {
        Text(
            text = stringResource(id = R.string.create_task_icon_picker_title_text),
            style = PomoTypography.body2,
            color = MaterialTheme.colorScheme.typoPrimary()
        )
        Box(modifier = Modifier
            .size(50.dp)
            .padding(top = 4.dp)
            .background(MaterialTheme.colorScheme.onBackground, RoundedCornerShape(6.dp))
            .clickable { actioner(CreateTaskAction.IconPickerClick) }) {
            if (state.newTaskIconRes == null) {
                Text(
                    modifier = Modifier.align(Center),
                    text = stringResource(id = R.string.create_task_icon_picker_no_icon_text),
                    style = PomoTypography.subtitle4,
                    color = MaterialTheme.colorScheme.primaryVariant()
                )
            } else {
                Icon(
                    modifier = Modifier.align(Center),
                    painter = painterResource(id = state.newTaskIconRes),
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.primary
                )
            }
        }
    }
}

@Composable
private fun TaskDurationSlider(state: CreateTaskViewState, actioner: (CreateTaskAction) -> Unit) {
    Column {
        Text(
            text = stringResource(id = R.string.create_task_snackbar_task_duration_title_text),
            style = PomoTypography.body2,
            color = MaterialTheme.colorScheme.typoPrimary()
        )
        PomoSlider(
            steps = STEP_TASK_DURATION_MINUTES.toInt(),
            valueRange = MIN_TASK_DURATION_MINUTES..MAX_TASK_DURATION_MINUTES,
            value = state.newTaskDurationSliderValue,
            onValueChangeFinished = { actioner(CreateTaskAction.TaskDurationSliderValueChange(it)) },
            tooltip = state.taskDurationMs.timeToToHoursMinutes(LocalContext.current)
        )
    }
}

@Composable
private fun WorkingSessionsSlider(
    state: CreateTaskViewState,
    actioner: (CreateTaskAction) -> Unit
) {
    Column {
        Text(
            text = stringResource(id = R.string.create_task_snackbar_working_sessions_title_text),
            style = PomoTypography.body2,
            color = MaterialTheme.colorScheme.typoPrimary()
        )
        PomoSlider(
            steps = STEP_WORKING_SESSIONS.toInt(),
            valueRange = MIN_WORKING_SESSIONS..MAX_WORKING_SESSIONS,
            value = state.newTaskWorkingSessionsValue,
            onValueChangeFinished = {
                actioner(CreateTaskAction.TaskWorkingSessionSliderValueChange(it))
            },
            tooltip = state.newTaskWorkingSessionsValue.toInt().toString()
        )
    }
}

@Composable
private fun BreakTimeSlider(state: CreateTaskViewState, actioner: (CreateTaskAction) -> Unit) {
    Column {
        Text(
            text = stringResource(id = R.string.create_task_snackbar_break_time_title_text),
            style = PomoTypography.body2,
            color = MaterialTheme.colorScheme.typoPrimary()
        )
        PomoSlider(
            steps = STEP_BREAK_TIME_MINUTES.toInt(),
            valueRange = MIN_BREAK_TIME_MINUTES..MAX_BREAK_TIME_MINUTES,
            value = state.newTaskBreakTimeValue,
            onValueChangeFinished = { actioner(CreateTaskAction.TaskBreakTimeSliderValueChange(it)) },
            tooltip = state.taskBreakTimeMs.timeToToHoursMinutes(LocalContext.current)
        )
    }
}

@Preview
@Composable
private fun CreateTaskPreview() {
    PomoTheme { CreateTaskScreen(CreateTaskViewState.Preview, {}, {}) }
}

