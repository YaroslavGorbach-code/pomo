package com.example.yaroslavhorach.pomo.glue.tasks.di

import com.example.yaroslavhorach.data.tasks.usecases.GetTodayTasksUseCaseImpl
import com.example.yaroslavhorach.domain.usecases.GetTodayTasksUseCase
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface UseCasesModule {

    @Binds
    fun bindsGetTodayTasksUseCase(getTodayTasksUseCase: GetTodayTasksUseCaseImpl): GetTodayTasksUseCase
}
