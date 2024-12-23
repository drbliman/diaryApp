package com.example.diaryapp.data.utils

import com.example.diaryapp.data.AppDatabase
import com.example.diaryapp.utils.SerializableTaskState
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

suspend fun launchGetTasks(database: AppDatabase): List<SerializableTaskState> {
    val gson = Gson()
    return withContext(Dispatchers.IO) {
        val tasks = database.taskDao().getAll()
        tasks.map { task ->
            gson.fromJson(task.task, SerializableTaskState::class.java)
        }
    }
}