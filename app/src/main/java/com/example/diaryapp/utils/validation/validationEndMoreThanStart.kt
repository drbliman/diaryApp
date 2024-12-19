package com.example.diaryapp.utils.validation

import android.content.Context
import android.widget.Toast
import com.example.diaryapp.R
import com.example.diaryapp.utils.TaskState

fun validationEndMoreThanStart(taskState: TaskState,  context: Context): Boolean {
    val messageValidationMore = context.getString(R.string.validation_end_more_than_start)
    val messageValidationEquals = context.getString(R.string.validation_end_equals_start)

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

    return taskState.selectedDateEndTimestamp.value.time > taskState.selectedDateStartTimestamp.value.time
}