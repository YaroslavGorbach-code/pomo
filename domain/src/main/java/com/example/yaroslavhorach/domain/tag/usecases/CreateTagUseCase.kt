package com.example.yaroslavhorach.domain.tag.usecases

import com.example.yaroslavhorach.domain.tag.models.Tag

interface CreateTagUseCase {
    suspend operator fun invoke(tag: Tag)
}