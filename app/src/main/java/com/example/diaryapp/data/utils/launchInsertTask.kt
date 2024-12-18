package com.example.diaryapp.data.utils

import com.example.diaryapp.data.AppDatabase
import com.example.diaryapp.data.Task
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

fun launchInsertTask(database: AppDatabase, task: Task) {
    kotlinx.coroutines.GlobalScope.launch(Dispatchers.IO) {
        database.taskDao().insertAll(task)
    }
}