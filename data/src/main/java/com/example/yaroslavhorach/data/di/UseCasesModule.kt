package com.example.yaroslavhorach.data.di

import com.example.yaroslavhorach.data.task.usecases.GetTaskAvailableColorsUseCaseImpl
import com.example.yaroslavhorach.data.task.usecases.GetTaskAvailableIconsUseCaseImpl
import com.example.yaroslavhorach.data.task.usecases.GetTodayTasksUseCaseImpl
import com.example.yaroslavhorach.domain.task.usecases.GetTaskAvailableColorsUseCase
import com.example.yaroslavhorach.domain.task.usecases.GetTaskAvailableIconsUseCase
import com.example.yaroslavhorach.domain.task.usecases.GetTodayTasksUseCase
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface UseCasesModule {

    @Binds
    fun bindsGetTodayTasksUseCase(getTodayTasksUseCase: GetTodayTasksUseCaseImpl): GetTodayTasksUseCase
    @Binds
    fun bindsGetTaskAvailableColorsUseCase(getTodayTasksUseCase: GetTaskAvailableColorsUseCaseImpl): GetTaskAvailableColorsUseCase
    @Binds
    fun bindsGetTaskAvailableIconsUseCase(getTodayTasksUseCase: GetTaskAvailableIconsUseCaseImpl): GetTaskAvailableIconsUseCase
}
