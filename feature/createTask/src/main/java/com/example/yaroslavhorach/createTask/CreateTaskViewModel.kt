package com.example.yaroslavhorach.createTask

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.lifecycle.viewModelScope
import com.example.yaroslavhorach.common.base.BaseViewModel
import com.example.yaroslavhorach.common.utill.combine
import com.example.yaroslavhorach.createTask.model.CreateTaskAction
import com.example.yaroslavhorach.createTask.model.CreateTaskUiMessage
import com.example.yaroslavhorach.createTask.model.CreateTaskViewState
import com.example.yaroslavhorach.createTask.model.CreateTaskViewState.Companion.MIN_TASK_POMODOUS
import com.example.yaroslavhorach.domain.tag.models.Tag
import com.example.yaroslavhorach.domain.tag.usecases.CreateTagUseCase
import com.example.yaroslavhorach.domain.tag.usecases.DeleteTagUseCase
import com.example.yaroslavhorach.domain.tag.usecases.GetAllTagsUseCase
import com.example.yaroslavhorach.domain.task.models.Task
import com.example.yaroslavhorach.domain.task.usecases.CreateTaskUseCase
import com.example.yaroslavhorach.domain.task.usecases.GetTaskAvailableColorsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class CreateTaskViewModel @Inject constructor(
    getTaskAvailableColorsUseCase: GetTaskAvailableColorsUseCase,
    val getAllTagsUseCase: GetAllTagsUseCase,
    val createTaskUseCase: CreateTaskUseCase,
    val crateTagUseCase: CreateTagUseCase,
    val deleteTagUseCase: DeleteTagUseCase
) : BaseViewModel<CreateTaskViewState, CreateTaskAction, CreateTaskUiMessage>() {

    override val pendingActions: MutableSharedFlow<CreateTaskAction> = MutableSharedFlow()

    private val estimatedTaskPomodousSliderValue = MutableStateFlow(MIN_TASK_POMODOUS)

    private val bottomShitContent: MutableStateFlow<CreateTaskViewState.BottomShit?> = MutableStateFlow(null)

    private val taskColor = MutableStateFlow(getTaskAvailableColorsUseCase().firstOrNull()?.let(::Color))

    private val selectedTags: MutableStateFlow<Set<Tag>> = MutableStateFlow(emptySet())

    private val taskDate: MutableStateFlow<Long> = MutableStateFlow(System.currentTimeMillis())

    private val taskPriority: MutableStateFlow<Task.Priority> = MutableStateFlow(Task.Priority.LOW)

    private val regularTaskDateRange: MutableStateFlow<LongRange?> = MutableStateFlow(null)

    override val state: StateFlow<CreateTaskViewState> = combine(
        estimatedTaskPomodousSliderValue,
        bottomShitContent,
        taskColor,
        taskDate,
        regularTaskDateRange,
        flowOf(getTaskAvailableColorsUseCase()),
        getAllTagsUseCase(),
        selectedTags,
        taskPriority,
        uiMessageManager.message
    ) { estimatedTaskPomodousSliderValue, bottomShit, taskColor, taskDate, taskRange, colors, availableTags, selectedTags, taskPriority, messages ->
        CreateTaskViewState(
            uiMessage = messages,
            taskPomodorosSliderValue = estimatedTaskPomodousSliderValue,
            selectedColor = taskColor,
            selectedDate = taskDate,
            colorPickerColors = colors.map(::Color),
            availableTags = availableTags,
            selectedTags = selectedTags,
            regularTaskDateRange = taskRange,
            bottomShitContent = bottomShit,
            selectedPriority = taskPriority
        )
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = CreateTaskViewState.Empty
    )

    init {
        pendingActions.onEach { action ->
            when (action) {
                is CreateTaskAction.DatePickerClicked -> {
                    submitMessage(CreateTaskUiMessage.ShowDatePicker(state.value.selectedDate))
                }
                is CreateTaskAction.TaskDurationSliderValueChanged -> {
                    estimatedTaskPomodousSliderValue.value = action.value
                }
                is CreateTaskAction.CreateRegularTaskClicked -> {
                    bottomShitContent.value = CreateTaskViewState.BottomShit.RegularTask
                    submitMessage(CreateTaskUiMessage.ShowBottomShit)
                }
                is CreateTaskAction.CreateTaskClicked -> {
                    state.value.taskTitle.validate()
//                    createTaskUseCase(state.value.newTaskDate)
                }
                is CreateTaskAction.TaskColorChosen -> {
                    taskColor.value = action.color
                }
                is CreateTaskAction.DateSelected -> {
                    taskDate.value = action.date
                }
                is CreateTaskAction.AddTagClicked -> {
                    if (state.value.availableTags.isEmpty()) {
                        bottomShitContent.value = CreateTaskViewState.BottomShit.CreateTag
                        submitMessage(CreateTaskUiMessage.ShowBottomShit)
                    } else {
                        bottomShitContent.value = CreateTaskViewState.BottomShit.SelectTag
                        submitMessage(CreateTaskUiMessage.ShowBottomShit)
                    }
                }
                is CreateTaskAction.PeriodPickerClicked -> {
                    submitMessage(
                        CreateTaskUiMessage.ShowRangePicker(
                            state.value.regularTaskDateRange?.start,
                            state.value.regularTaskDateRange?.endInclusive
                        )
                    )
                }
                is CreateTaskAction.DtaRangeSelected -> {
                    regularTaskDateRange.value = action.startDate..action.endDate
                }
                is CreateTaskAction.CreateTagClicked -> {
                    bottomShitContent.value = CreateTaskViewState.BottomShit.CreateTag
                    submitMessage(CreateTaskUiMessage.ShowBottomShit)
                }
                is CreateTaskAction.TagSelected -> {
                    selectedTags.update { tags ->
                        tags.toMutableSet().apply {
                            if (add(action.tag).not()) remove(action.tag)
                        }
                    }
                }
                is CreateTaskAction.SelectTagPrimaryBtnClicked,
                is CreateTaskAction.SelectTagSecondaryBtnClicked -> {
                    submitMessage(CreateTaskUiMessage.HideBottomShit)
                }
                is CreateTaskAction.PrioritySelected -> {
                    taskPriority.value = action.priority
                }
                is CreateTaskAction.CreatePrimaryBtnClicked -> {
                    crateTagUseCase(Tag(name = action.tagName, color = action.color.toArgb()))

                    bottomShitContent.value = CreateTaskViewState.BottomShit.SelectTag
                    submitMessage(CreateTaskUiMessage.ShowBottomShit)
                }
                is CreateTaskAction.CreateTagSecondaryBtnClicked -> {
                    bottomShitContent.value = CreateTaskViewState.BottomShit.SelectTag
                    submitMessage(CreateTaskUiMessage.ShowBottomShit)
                }
                is CreateTaskAction.DeleteTag -> {
                    selectedTags.update { tags ->
                        tags.toMutableSet().apply {
                            remove(find { it.id == action.id })
                        }
                    }
                    deleteTagUseCase(action.id)
                }
            }
        }.launchIn(viewModelScope)
    }
}
