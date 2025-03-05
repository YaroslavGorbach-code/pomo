package com.example.yaroslavhorach.domain.task.usecases

interface GetTaskAvailableColorsUseCase {
    operator fun invoke() : List<Int>
}