package com.example.yaroslavhorach.data.task.usecases

import com.example.yaroslavhorach.designsystem.theme.graphics.PomoIcons.TaskCategoriesIcons
import com.example.yaroslavhorach.domain.task.usecases.GetTaskAvailableIconsUseCase
import javax.inject.Inject

class GetTaskAvailableIconsUseCaseImpl @Inject constructor() : GetTaskAvailableIconsUseCase {
    override fun invoke(): List<Int> {
        return TaskCategoriesIcons
    }
}