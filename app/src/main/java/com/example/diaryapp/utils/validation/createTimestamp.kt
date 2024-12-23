package com.example.diaryapp.utils.validation

import com.example.diaryapp.utils.TaskState
import com.example.diaryapp.utils.convertToTimestamp

fun createTimestamp(taskState: TaskState) {
    taskState.selectedDateStartTimestamp.value = convertToTimestamp(
        taskState.selectedDateStartString.value,
        taskState.selectedTimeStartString.value
    )
    taskState.selectedDateEndTimestamp.value = convertToTimestamp(
        taskState.selectedDateStartString.value,
        taskState.selectedTimeEndString.value
    )
}