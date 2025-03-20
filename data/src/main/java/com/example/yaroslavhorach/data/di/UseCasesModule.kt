package com.example.yaroslavhorach.data.di

import com.example.yaroslavhorach.data.tag.usecases.CreateTagUseCaseImpl
import com.example.yaroslavhorach.data.tag.usecases.DeleteTagUseCaseImpl
import com.example.yaroslavhorach.data.tag.usecases.GetAllTagsUseCaseImpl
import com.example.yaroslavhorach.data.task.usecases.CreateTaskUseCaseImpl
import com.example.yaroslavhorach.data.task.usecases.GetTaskAvailableColorsUseCaseImpl
import com.example.yaroslavhorach.data.task.usecases.GetTodayTasksUseCaseImpl
import com.example.yaroslavhorach.domain.tag.usecases.CreateTagUseCase
import com.example.yaroslavhorach.domain.tag.usecases.DeleteTagUseCase
import com.example.yaroslavhorach.domain.tag.usecases.GetAllTagsUseCase
import com.example.yaroslavhorach.domain.task.usecases.CreateTaskUseCase
import com.example.yaroslavhorach.domain.task.usecases.GetTaskAvailableColorsUseCase
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
    fun bindsCreateTaskUseCase(createTaskUseCase: CreateTaskUseCaseImpl): CreateTaskUseCase

    @Binds
    fun bindsGetAllTagsUseCase(getAllTagsUseCase: GetAllTagsUseCaseImpl ): GetAllTagsUseCase

    @Binds
    fun bindsCreateTagUseCase(createTagUseCase: CreateTagUseCaseImpl): CreateTagUseCase

    @Binds
    fun bindsDeleteTagUseCase(deleteTagUseCase: DeleteTagUseCaseImpl): DeleteTagUseCase
}
