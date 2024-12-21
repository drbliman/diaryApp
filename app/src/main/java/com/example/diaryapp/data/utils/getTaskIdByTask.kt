package com.example.diaryapp.data.utils

import com.example.diaryapp.data.AppDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

suspend fun getTaskIdByTask(database: AppDatabase, task: String): Int? {
    return withContext(Dispatchers.IO) {
        database.taskDao().findIdByTask(task)
    }
}