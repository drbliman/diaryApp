package com.example.diaryapp.utils.validation

import com.example.diaryapp.utils.TaskState

fun validationEndMoreThanStart(taskState: TaskState): Boolean {
    taskState.validationEndMoreThanStart.value =
        taskState.selectedDateEndTimestamp.value.time > taskState.selectedDateStartTimestamp.value.time
    return taskState.selectedDateEndTimestamp.value.time > taskState.selectedDateStartTimestamp.value.time
}