package com.example.yaroslavhorach.domain.tag

import com.example.yaroslavhorach.domain.tag.models.Tag
import kotlinx.coroutines.flow.Flow

interface TagRepository {
    fun getAllTags(): Flow<List<Tag>>
    suspend fun crateTag(tag: Tag)
    suspend fun deleteTag(tagId: Long)
}