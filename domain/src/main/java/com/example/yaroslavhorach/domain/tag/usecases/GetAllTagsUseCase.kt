package com.example.yaroslavhorach.domain.tag.usecases

import com.example.yaroslavhorach.domain.tag.models.Tag
import kotlinx.coroutines.flow.Flow

interface GetAllTagsUseCase {
    operator fun invoke(): Flow<List<Tag>>
}