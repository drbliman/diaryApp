package com.example.diaryapp.utils.validation

import android.content.Context
import android.widget.Toast
import com.example.diaryapp.R
import com.example.diaryapp.data.AppDatabase
import com.example.diaryapp.data.utils.launchGetTasks
import com.example.diaryapp.utils.TaskState
import kotlinx.coroutines.*

fun validationConflictOfTasks(taskState: TaskState, context: Context, dataBase: AppDatabase) {
    val selectedTimeStart = taskState.selectedDateStartTimestamp.value.time
    val messageValidationConflict = context.getString(R.string.validation_conflict_of_tasks)
    CoroutineScope(Dispatchers.Main).launch {
        val tasks = withContext(Dispatchers.IO) {
            launchGetTasks(dataBase)
        }

        for (task in tasks) {
            val taskTimeStart = task.selectedDateStartTimestamp
            val taskTimeEnd = task.selectedDateEndTimestamp

            if (selectedTimeStart in taskTimeStart until taskTimeEnd) {
                Toast.makeText(context, messageValidationConflict, Toast.LENGTH_LONG).show()
                break
            }
        }
    }
}