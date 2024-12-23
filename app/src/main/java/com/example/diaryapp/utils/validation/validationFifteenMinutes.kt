package com.example.diaryapp.utils.validation

import com.example.diaryapp.utils.TaskState

fun validationFifteenMinutes(taskState: TaskState): Boolean {

    val startTime = taskState.selectedDateStartTimestamp.value.time
    val endTime = taskState.selectedDateEndTimestamp.value.time

    val difference = (endTime - startTime) >= 15 * 60 * 1000
    taskState.validationFifteenMinutes.value = difference

    return difference
}