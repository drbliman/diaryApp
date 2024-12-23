package com.example.diaryapp.utils.validation

import com.example.diaryapp.data.AppDatabase
import com.example.diaryapp.utils.TaskState

suspend fun validation(
    taskState: TaskState,
    dataBase: AppDatabase
) {
    val isEndMoreThanStartValid = validationEndMoreThanStart(taskState)
    val isFifteenMinutesValid = validationFifteenMinutes(taskState)
    val isConflictFree = validationConflictOfTasks(taskState, dataBase)

    taskState.validation.value = isEndMoreThanStartValid && isFifteenMinutesValid && isConflictFree
}