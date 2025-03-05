package com.example.yaroslavhorach.createTask

import androidx.compose.ui.graphics.Color
import androidx.lifecycle.viewModelScope
import com.example.yaroslavhorach.common.base.BaseViewModel
import com.example.yaroslavhorach.common.utill.combine
import com.example.yaroslavhorach.createTask.model.CreateTaskAction
import com.example.yaroslavhorach.createTask.model.CreateTaskUiMessage
import com.example.yaroslavhorach.createTask.model.CreateTaskViewState
import com.example.yaroslavhorach.createTask.model.CreateTaskViewState.Companion.MIN_BREAK_TIME_MINUTES
import com.example.yaroslavhorach.createTask.model.CreateTaskViewState.Companion.MIN_TASK_DURATION_MINUTES
import com.example.yaroslavhorach.createTask.model.CreateTaskViewState.Companion.MIN_WORKING_SESSIONS
import com.example.yaroslavhorach.domain.task.usecases.GetTaskAvailableColorsUseCase
import com.example.yaroslavhorach.domain.task.usecases.GetTaskAvailableIconsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class CreateTaskViewModel @Inject constructor(
    val getTaskAvailableColorsUseCase: GetTaskAvailableColorsUseCase,
    val getTaskAvailableIconsUseCase: GetTaskAvailableIconsUseCase
) : BaseViewModel<CreateTaskViewState, CreateTaskAction, CreateTaskUiMessage>() {

    override val pendingActions: MutableSharedFlow<CreateTaskAction> = MutableSharedFlow()

    private val taskDurationSliderValue = MutableStateFlow(MIN_TASK_DURATION_MINUTES)

    private val taskBreakTimeSliderValue = MutableStateFlow(MIN_BREAK_TIME_MINUTES)

    private val taskWorkingSessionSliderValue = MutableStateFlow(MIN_WORKING_SESSIONS)

    private val bottomShitContent: MutableStateFlow<CreateTaskViewState.BottomShit?> =
        MutableStateFlow(null)

    private val taskColor = MutableStateFlow(CreateTaskViewState.DEFAULT_TASK_COLOR)

    private val taskIcon: MutableStateFlow<Int?> = MutableStateFlow(null)

    private val taskDate: MutableStateFlow<Long?> = MutableStateFlow(null)

    private val regularTaskDateRange: MutableStateFlow<LongRange?> = MutableStateFlow(null)

    private val taskTime: MutableStateFlow<String?> = MutableStateFlow(null)

    private val taskTitle: MutableStateFlow<String?> = MutableStateFlow(null)

    override val state: StateFlow<CreateTaskViewState> = combine(
        taskDurationSliderValue,
        taskBreakTimeSliderValue,
        taskWorkingSessionSliderValue,
        bottomShitContent,
        taskColor,
        taskIcon,
        taskDate,
        taskTime,
        regularTaskDateRange,
        taskTitle,
        uiMessageManager.message
    ) { durationSliderValue, breakTimeSliderValue, workingSessionSliderValue, bottomShit, color, icon, taskDate, taskTime, taskRange, taskTitle, messages ->
        CreateTaskViewState(
            uiMessage = messages,
            newTaskDurationSliderValue = durationSliderValue,
            newTaskBreakTimeValue = breakTimeSliderValue,
            newTaskWorkingSessionsValue = workingSessionSliderValue,
            newTaskColor = color,
            newTaskIconRes = icon,
            newTaskDate = taskDate,
            newTaskTime = taskTime,
            newRegularTaskDateRange = taskRange,
            bottomShitContent = bottomShit,
            newTaskTitle = taskTitle
        )
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = CreateTaskViewState.Empty
    )

    init {
        pendingActions.onEach { action ->
            when (action) {
                is CreateTaskAction.ColorPickerClick -> {
                    val colors = getTaskAvailableColorsUseCase()
                    bottomShitContent.value = CreateTaskViewState.BottomShit.ColorPicker(colors)
                    submitMessage(CreateTaskUiMessage.ShowBottomShit)
                }

                is CreateTaskAction.DataPickerClick -> {
                    submitMessage(CreateTaskUiMessage.ShowDatePicker(state.value.newTaskDate))
                }

                is CreateTaskAction.IconPickerClick -> {
                    val icons = getTaskAvailableIconsUseCase()
                    bottomShitContent.value = CreateTaskViewState.BottomShit.IconPicker(icons)
                    submitMessage(CreateTaskUiMessage.ShowBottomShit)
                }

                is CreateTaskAction.TaskDurationSliderValueChange -> {
                    taskDurationSliderValue.value = action.value
                }

                is CreateTaskAction.TaskBreakTimeSliderValueChange -> {
                    taskBreakTimeSliderValue.value = action.value
                }

                is CreateTaskAction.TaskWorkingSessionSliderValueChange -> {
                    taskWorkingSessionSliderValue.value = action.value
                }

                is CreateTaskAction.TimePickerClick -> {
                    submitMessage(CreateTaskUiMessage.ShowTimePicker)
                }

                is CreateTaskAction.TypeTitle -> {
                    taskTitle.value = action.text
                }

                is CreateTaskAction.CreateRegularTaskClick -> {
                    bottomShitContent.value = CreateTaskViewState.BottomShit.RegularTask
                    submitMessage(CreateTaskUiMessage.ShowBottomShit)
                }

                is CreateTaskAction.CreateTaskClick -> {

                }

                is CreateTaskAction.ChoseColor -> {
                    taskColor.value = Color(action.color)
                    submitMessage(CreateTaskUiMessage.HideBottomShit)
                }

                is CreateTaskAction.ChoseIcon -> {
                    taskIcon.value = action.iconRes
                    submitMessage(CreateTaskUiMessage.HideBottomShit)
                }

                is CreateTaskAction.SelectDate -> {
                    taskDate.value = action.date
                }

                is CreateTaskAction.SelectTime -> {
                    taskTime.value = action.time
                }

                is CreateTaskAction.PeriodPickerClick -> {
                    submitMessage(
                        CreateTaskUiMessage.ShowRangePicker(
                            state.value.newRegularTaskDateRange?.start,
                            state.value.newRegularTaskDateRange?.endInclusive
                        )
                    )
                }

                is CreateTaskAction.SelectDateRange -> {
                    regularTaskDateRange.value = action.startDate..action.endDate
                }
            }
        }.launchIn(viewModelScope)
    }
}
