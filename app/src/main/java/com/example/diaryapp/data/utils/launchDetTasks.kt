package com.example.diaryapp.data.utils

import com.example.diaryapp.data.AppDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

fun launchPrintTasks(database: AppDatabase) {
    kotlinx.coroutines.GlobalScope.launch(Dispatchers.IO) {
        val tasks = database.taskDao().getAll()
        tasks.forEach {
            println(it)
        }
    }
}