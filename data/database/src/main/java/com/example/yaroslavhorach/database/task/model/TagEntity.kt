package com.example.yaroslavhorach.database.task.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.yaroslavhorach.domain.tag.models.Tag

@Entity(tableName = "tags")
data class TagEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long,
    val name: String,
    val color: Int,
)

fun TagEntity.asDomainModel() = Tag(
    id = id,
    name = name,
    color = color
)

fun Tag.asEntityModel() = TagEntity(
    id = id,
    name = name,
    color = color,
)
