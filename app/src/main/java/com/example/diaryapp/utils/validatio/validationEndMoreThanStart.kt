package com.example.diaryapp.utils.validatio

import android.content.Context
import android.widget.Toast
import com.example.diaryapp.R
import com.example.diaryapp.utils.TaskState
import com.example.diaryapp.utils.convertToTimestamp

fun validationEndMoreThanStart(taskState: TaskState, context: Context) {
    val messageValidationMore = context.getString(R.string.validation_end_more_than_start)
    val messageValidationEquals = context.getString(R.string.validation_end_equals_start)

    taskState.selectedDateStartTimestamp.value = convertToTimestamp(
        taskState.selectedDateStartString.value,
        taskState.selectedTimeStartString.value
    )
    taskState.selectedDateEndTimestamp.value = convertToTimestamp(
        taskState.selectedDateStartString.value,
        taskState.selectedTimeEndString.value
    )

    taskState.validEndMoreThanStart.value =
        taskState.selectedDateEndTimestamp.value.time > taskState.selectedDateStartTimestamp.value.time

    if(
        taskState.selectedDateEndTimestamp.value.time < taskState.selectedDateStartTimestamp.value.time
    ) {
        Toast.makeText(context, messageValidationMore, Toast.LENGTH_LONG).show()
    }

    if(
        taskState.selectedDateEndTimestamp.value.time == taskState.selectedDateStartTimestamp.value.time
    ) {
        Toast.makeText(context, messageValidationEquals, Toast.LENGTH_LONG).show()
    }
}