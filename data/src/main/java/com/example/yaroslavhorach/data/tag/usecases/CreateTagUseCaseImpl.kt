package com.example.yaroslavhorach.data.tag.usecases

import com.example.yaroslavhorach.domain.tag.TagRepository
import com.example.yaroslavhorach.domain.tag.models.Tag
import com.example.yaroslavhorach.domain.tag.usecases.CreateTagUseCase
import javax.inject.Inject

class CreateTagUseCaseImpl @Inject constructor(
    private val tagRepository: TagRepository
) : CreateTagUseCase {

    override suspend fun invoke(tag: Tag) {
        return tagRepository.crateTag(tag)
    }
}