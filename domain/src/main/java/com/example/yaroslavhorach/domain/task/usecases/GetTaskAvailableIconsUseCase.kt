package com.example.yaroslavhorach.domain.task.usecases

interface GetTaskAvailableIconsUseCase {
    operator fun invoke() : List<Int>
}