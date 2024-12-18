package com.example.diaryapp.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tasks")
class Task(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val task: String?,
) {
    override fun toString(): String {
        return "Task(id=$id, task=$task)"
    }
}