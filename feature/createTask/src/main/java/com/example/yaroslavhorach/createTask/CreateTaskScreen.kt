package com.example.yaroslavhorach.createTask

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.ime
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.ModalBottomSheetLayout
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.material3.AssistChip
import androidx.compose.material3.AssistChipDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.yaroslavhorach.CreateTask.R
import com.example.yaroslavhorach.common.utill.TimeFormat
import com.example.yaroslavhorach.common.utill.getTomorrowInMs
import com.example.yaroslavhorach.common.utill.isAfterTomorrow
import com.example.yaroslavhorach.common.utill.isToday
import com.example.yaroslavhorach.common.utill.isTomorrow
import com.example.yaroslavhorach.common.utill.timeToToHoursMinutes
import com.example.yaroslavhorach.common.utill.toReadableDateShort
import com.example.yaroslavhorach.createTask.model.CreateTaskAction
import com.example.yaroslavhorach.createTask.model.CreateTaskUiMessage
import com.example.yaroslavhorach.createTask.model.CreateTaskViewState
import com.example.yaroslavhorach.createTask.model.CreateTaskViewState.Companion.MAX_TASK_POMODOUS
import com.example.yaroslavhorach.createTask.model.CreateTaskViewState.Companion.MIN_TASK_POMODOUS
import com.example.yaroslavhorach.createTask.model.CreateTaskViewState.Companion.POMODOUS_SLIDER_STEPS
import com.example.yaroslavhorach.designsystem.theme.Alert
import com.example.yaroslavhorach.designsystem.theme.AmazonOrange
import com.example.yaroslavhorach.designsystem.theme.ElectricGreen
import com.example.yaroslavhorach.designsystem.theme.PomoTheme
import com.example.yaroslavhorach.designsystem.theme.PomoTypography
import com.example.yaroslavhorach.designsystem.theme.White
import com.example.yaroslavhorach.designsystem.theme.components.PomoBackground
import com.example.yaroslavhorach.designsystem.theme.components.PomoSlider
import com.example.yaroslavhorach.designsystem.theme.components.PrimaryButton
import com.example.yaroslavhorach.designsystem.theme.components.input_fields.PomoInputField
import com.example.yaroslavhorach.designsystem.theme.graphics.PomoIcons
import com.example.yaroslavhorach.designsystem.theme.primaryIcon
import com.example.yaroslavhorach.designsystem.theme.secondaryIcon
import com.example.yaroslavhorach.designsystem.theme.typoPrimary
import com.example.yaroslavhorach.designsystem.theme.typoSecondary
import com.example.yaroslavhorach.domain.task.models.Task
import com.example.yaroslavhorach.ui.utils.conditional
import java.util.Date
import kotlin.math.roundToInt

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

@Composable
internal fun CreateTaskScreen(
    screenState: CreateTaskViewState,
    onMessageShown: (id: Long) -> Unit,
    actioner: (CreateTaskAction) -> Unit,
) {
    val keyboardController = LocalSoftwareKeyboardController.current

    val scrollableState = rememberScrollState()
    val modalSheetState = rememberModalBottomSheetState(
        initialValue = ModalBottomSheetValue.Hidden,
        confirmValueChange = {
            keyboardController?.hide()
            it != ModalBottomSheetValue.HalfExpanded
        },
        skipHalfExpanded = true
    )

    when (val message = screenState.uiMessage?.message) {
        is CreateTaskUiMessage.ShowBottomShit -> {
            LaunchedEffect(
                key1 = screenState.uiMessage.id,
                block = {
                    modalSheetState.show()
                    onMessageShown(screenState.uiMessage.id)
                })
        }
        is CreateTaskUiMessage.HideBottomShit -> {
            LaunchedEffect(
                key1 = screenState.uiMessage.id,
                block = {
                    modalSheetState.hide()
                    onMessageShown(screenState.uiMessage.id)
                })
        }
        is CreateTaskUiMessage.ShowDatePicker -> {
            TaskDatePicker(message.selectedDate, actioner) { onMessageShown(screenState.uiMessage.id) }
        }
        is CreateTaskUiMessage.ShowRangePicker -> {
            DateRangePickerDialog(
                initialStart = screenState.regularTaskDateRange?.start,
                initialEnd = screenState.regularTaskDateRange?.endInclusive,
                actioner
            ) { onMessageShown(screenState.uiMessage.id) }
        }
        null -> {}
    }

    ModalBottomSheetLayout(
        modifier = Modifier
            .fillMaxSize()
            .windowInsetsPadding(WindowInsets.ime),
        sheetShape = RoundedCornerShape(topEnd = 16.dp, topStart = 16.dp),
        sheetBackgroundColor = MaterialTheme.colorScheme.background,
        sheetState = modalSheetState,
        sheetContent = {
            when (screenState.bottomShitContent) {
                is CreateTaskViewState.BottomShit.RegularTask -> {
                    CreateRegularTaskContent(screenState, actioner)
                }
                is CreateTaskViewState.BottomShit.SelectTag -> {
                    SelectTagsContent(screenState, actioner)
                }
                is CreateTaskViewState.BottomShit.CreateTag -> {
                    CreateTagContent(screenState, actioner)
                }
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
        TitleTextField(screenState)
        Spacer(modifier = Modifier.height(20.dp))
        EstimatedPomodousSlider(screenState, actioner)
        Spacer(modifier = Modifier.height(16.dp))
        ColorPicker(screenState, actioner)
        Spacer(modifier = Modifier.height(20.dp))
        DueDatePicker(screenState, actioner)
        Spacer(modifier = Modifier.height(20.dp))
        PriorityPicker(screenState, actioner)
        Spacer(modifier = Modifier.height(20.dp))
        Tags(screenState, actioner)
        Spacer(modifier = Modifier.height(20.dp))
        Spacer(modifier = Modifier.weight(1f))
        PrimaryButton(
            modifier = Modifier.fillMaxWidth(),
            text = stringResource(id = R.string.create_task_create_task_btn_text)
        ) { actioner(CreateTaskAction.CreateTaskClicked) }
        Spacer(modifier = Modifier.height(20.dp))
        // TODO: develop regular task feature in the future
//        PrimaryVariantButton(
//            modifier = Modifier.fillMaxWidth(),
//            text = stringResource(id = R.string.create_task_create_regular_task_btn_text)
//        ) { actioner(CreateTaskAction.CreateRegularTaskClick) }
//        Spacer(modifier = Modifier.height(20.dp))
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
private fun TitleTextField(state: CreateTaskViewState) {
    PomoInputField(
        modifier = Modifier.fillMaxWidth(),
        title = stringResource(id = R.string.create_task_text_field_title_title_text),
        hint = stringResource(id = R.string.create_task_text_field_hint_title_text),
        state = state.taskTitle,
        maxLength = CreateTaskViewState.TITLE_MAX_LENGTH
    )
}

@Composable
private fun ColumnScope.ColorPicker(state: CreateTaskViewState, actioner: (CreateTaskAction) -> Unit) {
    Text(
        text = stringResource(id = R.string.create_task_color_picker_title_text),
        style = PomoTypography.subtitle3,
        color = MaterialTheme.colorScheme.typoPrimary()
    )
    Spacer(Modifier.size(8.dp))
    LazyRow {
        itemsIndexed(state.colorPickerColors) { index, color ->
            if (index > 0) {
                Spacer(Modifier.size(16.dp))
            }
            Box(
                modifier = Modifier
                    .size(50.dp)
                    .clickable { actioner(CreateTaskAction.TaskColorChosen(color)) }
                    .background(MaterialTheme.colorScheme.onBackground, RoundedCornerShape(6.dp))
                    .padding(6.dp)
                    .background(color, RoundedCornerShape(6.dp))
            ) {
                if (state.selectedColor == color) {
                    Icon(
                        modifier = Modifier
                            .align(Center),
                        tint = White,
                        painter = painterResource(id = PomoIcons.Check),
                        contentDescription = null,
                    )
                }
            }
        }
    }
}

@Composable
private fun ColumnScope.DueDatePicker(state: CreateTaskViewState, actioner: (CreateTaskAction) -> Unit) {
    val primaryColor = MaterialTheme.colorScheme.primary

    Text(
        text = stringResource(id = R.string.create_task_due_date_picker_title_text),
        style = PomoTypography.subtitle3,
        color = MaterialTheme.colorScheme.typoPrimary()
    )
    Spacer(Modifier.size(8.dp))
    Row {
        Column {
            Box(
                modifier = Modifier
                    .size(50.dp)
                    .clickable { actioner(CreateTaskAction.DateSelected(Date().time)) }
                    .background(MaterialTheme.colorScheme.onBackground, RoundedCornerShape(6.dp))
                    .conditional(state.selectedDate?.isToday() == true) {
                        border(
                            1.dp, primaryColor, RoundedCornerShape(6.dp)
                        )
                    },
                ) {
                Icon(
                    modifier = Modifier
                        .align(Center),
                    tint = MaterialTheme.colorScheme.primary,
                    painter = painterResource(id = PomoIcons.Today),
                    contentDescription = null,
                )
            }
            Spacer(Modifier.size(4.dp))
            Text(
                modifier = Modifier
                    .align(CenterHorizontally),
                text = stringResource(R.string.create_task_due_date_picker_today_text),
                style = PomoTypography.body4,
                color = MaterialTheme.colorScheme.typoPrimary()
            )
        }
        Spacer(Modifier.size(16.dp))
        Column {
            Box(
                modifier = Modifier
                    .size(50.dp)
                    .clickable { actioner(CreateTaskAction.DateSelected(getTomorrowInMs())) }
                    .background(MaterialTheme.colorScheme.onBackground, RoundedCornerShape(6.dp))
                    .conditional(state.selectedDate?.isTomorrow() == true) {
                        border(
                            1.dp, primaryColor, RoundedCornerShape(6.dp)
                        )
                    }
                ) {
                Icon(
                    modifier = Modifier
                        .align(Center),
                    tint = MaterialTheme.colorScheme.primary,
                    painter = painterResource(id = PomoIcons.Tomorrow),
                    contentDescription = null,
                )
            }
            Spacer(Modifier.size(4.dp))
            Text(
                modifier = Modifier
                    .align(CenterHorizontally),
                text = stringResource(R.string.create_task_due_date_picker_tomorrow_text),
                style = PomoTypography.body4,
                color = MaterialTheme.colorScheme.typoPrimary()
            )
        }
        Spacer(Modifier.size(16.dp))
        Column {
            val ifAfterTomorrow = state.selectedDate?.isAfterTomorrow() == true

            Box(
                modifier = Modifier
                    .size(50.dp)
                    .clickable { actioner(CreateTaskAction.DatePickerClicked) }
                    .background(MaterialTheme.colorScheme.onBackground, RoundedCornerShape(6.dp))
                    .conditional(ifAfterTomorrow) { border(1.dp, primaryColor, RoundedCornerShape(6.dp)) }
            ) {
                if (ifAfterTomorrow) {
                    Text(
                        modifier = Modifier
                            .align(Center),
                        text = state.selectedDate?.toReadableDateShort().toString(),
                        style = PomoTypography.subtitle5,
                        color = MaterialTheme.colorScheme.primary
                    )
                } else {
                    Icon(
                        modifier = Modifier
                            .align(Center),
                        tint = MaterialTheme.colorScheme.primary,
                        painter = painterResource(id = PomoIcons.Calendar),
                        contentDescription = null,
                    )
                }
            }
            Spacer(Modifier.size(4.dp))
            Text(
                modifier = Modifier
                    .align(CenterHorizontally),
                text = stringResource(R.string.create_task_due_date_picker_pick_date_text),
                style = PomoTypography.body4,
                color = MaterialTheme.colorScheme.typoPrimary()
            )
        }
    }
}

@Composable
private fun ColumnScope.PriorityPicker(state: CreateTaskViewState, actioner: (CreateTaskAction) -> Unit) {
    val primaryColor = MaterialTheme.colorScheme.primary

    Text(
        text = stringResource(id = R.string.create_task_priority_picker_title_text),
        style = PomoTypography.subtitle3,
        color = MaterialTheme.colorScheme.typoPrimary()
    )
    Spacer(Modifier.size(8.dp))
    Row {
        Column {
            Box(
                modifier = Modifier
                    .size(50.dp)
                    .clickable { actioner(CreateTaskAction.PrioritySelected(Task.Priority.LOW)) }
                    .background(MaterialTheme.colorScheme.onBackground, RoundedCornerShape(6.dp))
                    .conditional(state.selectedPriority == Task.Priority.LOW) {
                        border(
                            1.dp, primaryColor, RoundedCornerShape(6.dp)
                        )
                    },
            ) {
                Icon(
                    modifier = Modifier
                        .align(Center),
                    tint = ElectricGreen,
                    painter = painterResource(id = PomoIcons.Flag),
                    contentDescription = null,
                )
            }
            Spacer(Modifier.size(4.dp))
            Text(
                modifier = Modifier
                    .align(CenterHorizontally),
                text = stringResource(R.string.create_task_priority_picker_low_text),
                style = PomoTypography.body4,
                color = MaterialTheme.colorScheme.typoPrimary()
            )
        }
        Spacer(Modifier.size(16.dp))
        Column {
            Box(
                modifier = Modifier
                    .size(50.dp)
                    .clickable { actioner(CreateTaskAction.PrioritySelected(Task.Priority.MEDIUM)) }
                    .background(MaterialTheme.colorScheme.onBackground, RoundedCornerShape(6.dp))
                    .conditional(state.selectedPriority == Task.Priority.MEDIUM) {
                        border(1.dp, primaryColor, RoundedCornerShape(6.dp))
                    },
            ) {
                Icon(
                    modifier = Modifier
                        .align(Center),
                    tint = AmazonOrange,
                    painter = painterResource(id = PomoIcons.Flag),
                    contentDescription = null,
                )
            }
            Spacer(Modifier.size(4.dp))
            Text(
                modifier = Modifier
                    .align(CenterHorizontally),
                text = stringResource(R.string.create_task_priority_picker_medium_text),
                style = PomoTypography.body4,
                color = MaterialTheme.colorScheme.typoPrimary()
            )
        }
        Spacer(Modifier.size(16.dp))
        Column {
            Box(
                modifier = Modifier
                    .size(50.dp)
                    .clickable { actioner(CreateTaskAction.PrioritySelected(Task.Priority.HIGH)) }
                    .background(MaterialTheme.colorScheme.onBackground, RoundedCornerShape(6.dp))
                    .conditional(state.selectedPriority == Task.Priority.HIGH) {
                        border(1.dp, primaryColor, RoundedCornerShape(6.dp))
                    },
            ) {
                Icon(
                    modifier = Modifier
                        .align(Center),
                    tint = Alert,
                    painter = painterResource(id = PomoIcons.Flag),
                    contentDescription = null,
                )
            }
            Spacer(Modifier.size(4.dp))
            Text(
                modifier = Modifier
                    .align(CenterHorizontally),
                text = stringResource(R.string.create_task_priority_picker_high_text),
                style = PomoTypography.body4,
                color = MaterialTheme.colorScheme.typoPrimary()
            )
        }
    }
}


@OptIn(ExperimentalLayoutApi::class)
@Composable
private fun ColumnScope.Tags(state: CreateTaskViewState, actioner: (CreateTaskAction) -> Unit) {
    Text(
        text = stringResource(id = R.string.create_task_tags_title_text),
        style = PomoTypography.subtitle3,
        color = MaterialTheme.colorScheme.typoPrimary()
    )
    Spacer(Modifier.size(8.dp))
    FlowRow(
        horizontalArrangement = Arrangement.spacedBy(16.dp), modifier = Modifier
    ) {
        state.selectedTags.forEach { tag ->
            AssistChip(
                onClick = { actioner(CreateTaskAction.TagSelected(tag)) },
                border = AssistChipDefaults.assistChipBorder(
                    borderColor = Color(tag.color)
                ), trailingIcon = {
                    Icon(painter = painterResource(PomoIcons.Close16), contentDescription = null)
                }, label = {
                    Text(text = "#" + tag.name, style = PomoTypography.subtitle3, color = Color(tag.color))
                }
            )
        }
        Box(
            modifier = Modifier
                .align(CenterVertically)
                .border(
                    width = 1.dp,
                    color = MaterialTheme.colorScheme.secondaryIcon(),
                    shape = RoundedCornerShape(12.dp)
                )
                .padding(vertical = 8.dp, horizontal = 12.dp),
        ) {
            Icon(
                modifier = Modifier
                    .size(16.dp)
                    .clickable { actioner(CreateTaskAction.AddTagClicked) },
                painter = painterResource(PomoIcons.Plus),
                contentDescription = null,
                tint = MaterialTheme.colorScheme.primaryIcon()
            )
        }
    }
}

@Composable
private fun EstimatedPomodousSlider(state: CreateTaskViewState, actioner: (CreateTaskAction) -> Unit) {
    Column {
        Text(
            text = stringResource(id = R.string.create_task_slider_estimated_pomodous_title_text),
            style = PomoTypography.subtitle3,
            color = MaterialTheme.colorScheme.typoPrimary()
        )
        Text(
            text = stringResource(id = R.string.create_task_slider_estimated_pomodous_subtitle_text),
            style = PomoTypography.body4,
            color = MaterialTheme.colorScheme.typoSecondary()
        )
        Spacer(Modifier.size(8.dp))
        PomoSlider(
            steps = POMODOUS_SLIDER_STEPS,
            valueRange = MIN_TASK_POMODOUS.. MAX_TASK_POMODOUS,
            value = state.taskPomodorosSliderValue,
            onValueChangeFinished = {
                actioner(CreateTaskAction.TaskDurationSliderValueChanged(it.roundToInt().toFloat())) },
            tooltip = state.taskPomodorosSliderValue.toInt().toString() + " "
                    + "(" + state.taskDurationMs.timeToToHoursMinutes(LocalContext.current, TimeFormat.SHORT) + ")"
        )
    }
}

@Preview
@Composable
private fun CreateTaskPreview() {
    PomoTheme {
        PomoBackground {
            CreateTaskScreen(CreateTaskViewState.Preview, {}, {})
        }
    }
}

