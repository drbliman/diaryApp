package com.example.diaryapp.utils.validation

import com.example.diaryapp.utils.TaskState

fun validationEndMoreThanStart(taskState: TaskState): Boolean {
    return taskState.selectedDateEndTimestamp.value.time > taskState.selectedDateStartTimestamp.value.time
}