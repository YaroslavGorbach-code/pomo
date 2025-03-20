package com.example.yaroslavhorach.data.task.usecases

import androidx.compose.ui.graphics.toArgb
import com.example.yaroslavhorach.designsystem.theme.TaskAndTagsBackgrounds
import com.example.yaroslavhorach.domain.task.usecases.GetTaskAvailableColorsUseCase
import javax.inject.Inject

class GetTaskAvailableColorsUseCaseImpl @Inject constructor() : GetTaskAvailableColorsUseCase {
    override fun invoke(): List<Int> {
        return TaskAndTagsBackgrounds.map { it.toArgb() }
    }
}