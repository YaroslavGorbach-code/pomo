

package com.example.yaroslavhorach.database.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.example.yaroslavhorach.database.task.model.TaskEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface TaskDao {
    @Query(
        value = """
        SELECT * FROM tasks
        WHERE id = :id
    """,
    )
    fun getTaskEntity(id: String): Flow<TaskEntity>

    @Query(value = "SELECT * FROM tasks")
    fun getTaskEntities(): Flow<List<TaskEntity>>

    @Upsert
    suspend fun upsertTasks(entities: List<TaskEntity>)

    @Query(
        value = """
            DELETE FROM tasks
            WHERE id in (:ids)
        """,
    )
    suspend fun deleteTasks(ids: List<String>)
}
