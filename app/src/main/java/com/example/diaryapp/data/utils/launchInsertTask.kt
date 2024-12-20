package com.example.diaryapp.data.utils

import com.example.diaryapp.data.AppDatabase
import com.example.diaryapp.data.Task
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

suspend fun launchInsertTask(database: AppDatabase, task: Task): Boolean {
    return try {
        withContext(Dispatchers.IO) {
            database.taskDao().insertAll(task)
        }
        true
    } catch (e: Exception) {
        e.printStackTrace()
        false
    }
}