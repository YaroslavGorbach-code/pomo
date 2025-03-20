package com.example.yaroslavhorach.data.tag.usecases

import com.example.yaroslavhorach.domain.tag.TagRepository
import com.example.yaroslavhorach.domain.tag.models.Tag
import com.example.yaroslavhorach.domain.tag.usecases.GetAllTagsUseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetAllTagsUseCaseImpl @Inject constructor(
    private val tagRepository: TagRepository
) : GetAllTagsUseCase {

    override fun invoke(): Flow<List<Tag>> {
        return tagRepository.getAllTags()
    }
}