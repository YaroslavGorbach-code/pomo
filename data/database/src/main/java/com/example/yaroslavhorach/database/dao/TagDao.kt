package com.example.yaroslavhorach.database.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.example.yaroslavhorach.database.task.model.TagEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface TagDao {
    @Query(value = "SELECT * FROM tags ORDER by id DESC")
    fun getTagsEntities(): Flow<List<TagEntity>>

    @Upsert
    suspend fun upsertTag(tags: List<TagEntity>)

    @Query(
        value = """
            DELETE FROM tags
            WHERE id in (:ids)
        """,
    )
    suspend fun deleteTags(ids: List<Long>)
}
