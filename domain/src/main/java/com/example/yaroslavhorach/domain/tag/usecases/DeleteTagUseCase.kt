package com.example.yaroslavhorach.domain.tag.usecases

interface DeleteTagUseCase {
    suspend operator fun invoke(id: Long)
}