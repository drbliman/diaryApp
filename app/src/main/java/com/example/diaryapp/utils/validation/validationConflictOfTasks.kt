package com.example.diaryapp.utils.validation

import com.example.diaryapp.data.AppDatabase
import com.example.diaryapp.data.utils.launchGetTasks
import com.example.diaryapp.utils.TaskState
import kotlinx.coroutines.*

suspend fun validationConflictOfTasks(
    taskState: TaskState,
    dataBase: AppDatabase
): Boolean {
    val selectedTimeStart = taskState.selectedDateStartTimestamp.value.time

    val tasks = withContext(Dispatchers.IO) {
        launchGetTasks(dataBase)
    }

    for (task in tasks) {
        val taskTimeStart = task.selectedDateStartTimestamp
        val taskTimeEnd = task.selectedDateEndTimestamp

        if (selectedTimeStart in taskTimeStart until taskTimeEnd) {
            taskState.validationConflictOfTasks.value = false
            return false
        }
    }

    taskState.validationConflictOfTasks.value = true

    return true
}