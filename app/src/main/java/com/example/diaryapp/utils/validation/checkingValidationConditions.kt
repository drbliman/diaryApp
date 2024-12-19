package com.example.diaryapp.utils.validation

import com.example.diaryapp.utils.TaskState

fun checkingValidationConditions(taskState: TaskState): Boolean {
    return taskState.selectedTimeStartString.value.trim().isNotEmpty() &&
        taskState.selectedDateStartString.value.trim().isNotEmpty() &&
        taskState.selectedTimeEndString.value.trim().isNotEmpty() &&
        !taskState.isTimePickerEndOpen.value &&
        !taskState.isTimePickerStartOpen.value &&
        !taskState.isDatePickerStartOpen.value
}