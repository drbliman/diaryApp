package com.example.diaryapp.data.utils

import com.example.diaryapp.data.AppDatabase
import com.example.diaryapp.data.Task
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

suspend fun deleteTaskByIdAndTask(database: AppDatabase, id: Int, task: String): Task? {
    return withContext(Dispatchers.IO) {
        val taskDao = database.taskDao()
        val existingTask = taskDao.getAll().find { it.id == id && it.task == task }
        if (existingTask != null) {
            taskDao.delete(existingTask)
            existingTask
        } else {
            null
        }
    }
}