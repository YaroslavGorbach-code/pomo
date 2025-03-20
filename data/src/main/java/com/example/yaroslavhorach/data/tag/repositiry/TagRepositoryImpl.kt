package com.example.yaroslavhorach.data.tag.repositiry

import com.example.yaroslavhorach.database.dao.TagDao
import com.example.yaroslavhorach.database.task.model.TagEntity
import com.example.yaroslavhorach.database.task.model.asDomainModel
import com.example.yaroslavhorach.database.task.model.asEntityModel
import com.example.yaroslavhorach.domain.tag.TagRepository
import com.example.yaroslavhorach.domain.tag.models.Tag
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class TagRepositoryImpl @Inject constructor(
    private val tagDao: TagDao
) : TagRepository {

    override fun getAllTags(): Flow<List<Tag>> {
        return tagDao.getTagsEntities()
            .map { entities -> entities.map(TagEntity::asDomainModel) }
    }

    override suspend fun crateTag(tag: Tag) {
        tagDao.upsertTag(listOf(tag.asEntityModel()))
    }

    override suspend fun deleteTag(tagId: Long) {
        tagDao.deleteTags(listOf(tagId))
    }
}