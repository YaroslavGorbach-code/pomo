package com.example.yaroslavhorach.data.tag.usecases

import com.example.yaroslavhorach.domain.tag.TagRepository
import com.example.yaroslavhorach.domain.tag.usecases.DeleteTagUseCase
import javax.inject.Inject

class DeleteTagUseCaseImpl @Inject constructor(
    private val tagRepository: TagRepository
) : DeleteTagUseCase {

    override suspend fun invoke(id: Long) {
        return tagRepository.deleteTag(id)
    }
}