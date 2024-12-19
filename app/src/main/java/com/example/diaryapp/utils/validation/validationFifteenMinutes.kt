package com.example.diaryapp.utils.validation

import android.content.Context
import android.widget.Toast
import com.example.diaryapp.R
import com.example.diaryapp.utils.TaskState

fun validationFifteenMinutes(taskState: TaskState, context: Context): Boolean {
    val messageValidationMore = context.getString(R.string.validation_fifteen_minutes)

    val startTime = taskState.selectedDateStartTimestamp.value.time
    val endTime = taskState.selectedDateEndTimestamp.value.time

    val difference = (endTime - startTime) >= 15 * 60 * 1000

    if (!difference) {
        Toast.makeText(context, messageValidationMore, Toast.LENGTH_LONG).show()
    }

    return difference
}