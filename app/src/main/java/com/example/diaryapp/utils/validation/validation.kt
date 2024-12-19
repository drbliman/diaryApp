package com.example.diaryapp.utils.validation

import android.content.Context
import com.example.diaryapp.data.AppDatabase
import com.example.diaryapp.utils.TaskState

fun validation (taskState: TaskState,  context: Context,  dataBase: AppDatabase) {
    validationConflictOfTasks(taskState, context, dataBase)

    val valid = validationEndMoreThanStart(taskState, context) && validationFifteenMinutes(taskState, context)

    taskState.validation.value = valid
}