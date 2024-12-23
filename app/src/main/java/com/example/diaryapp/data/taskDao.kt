package com.example.diaryapp.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface TaskDao {
    @Query("SELECT id FROM tasks WHERE task = :task LIMIT 1")
    fun findIdByTask(task: String): Int?

    @Query("SELECT * FROM tasks")
    fun getAll(): List<Task>

    @Query("SELECT * FROM tasks WHERE id IN (:taskIds)")
    fun loadAllByIds(taskIds: IntArray): List<Task>

    @Insert
    fun insertAll(vararg tasks: Task)

    @Delete
    fun delete(task: Task)
}