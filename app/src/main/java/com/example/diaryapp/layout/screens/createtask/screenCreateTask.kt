package com.example.diaryapp.layout.screens.createtask

import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import com.example.diaryapp.R
import com.example.diaryapp.data.AppDatabase
import com.example.diaryapp.data.Task
import com.example.diaryapp.data.utils.launchInsertTask
import com.example.diaryapp.data.utils.launchPrintTasks
import com.example.diaryapp.layout.components.TextFiledWithTitle
import com.example.diaryapp.layout.screens.createtask.components.ButtonTimePicker
import com.example.diaryapp.layout.screens.createtask.components.DatePickerDialogExample
import com.example.diaryapp.layout.screens.createtask.components.PickerType
import com.example.diaryapp.layout.screens.createtask.components.TimePickerDialogExample
import com.example.diaryapp.utils.TaskState
import com.example.diaryapp.utils.toSerializableTaskState
import com.example.diaryapp.utils.validatio.checkingValidationConditions
import com.example.diaryapp.utils.validatio.validationEndMoreThanStart
import com.google.gson.Gson

@Composable
fun ScreenCreateTask(dataBase: AppDatabase) {
    val context = LocalContext.current

    val taskState = remember { TaskState() }

    val messageTitleTask = context.getString(R.string.title_task)
    val messageDescriptionTask = context.getString(R.string.description_task)

    val focusManager = LocalFocusManager.current
    val keyboardController = LocalSoftwareKeyboardController.current

    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .pointerInput(Unit) {
                detectTapGestures(
                    onTap = {
                        focusManager.clearFocus()
                        keyboardController?.hide()
                    }
                )
            },
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(paddingValues),
        ) {
            ButtonTimePicker(taskState, PickerType.DATE_START)
            ButtonTimePicker(taskState, PickerType.TIME_START)
            ButtonTimePicker(taskState, PickerType.TIME_END)
            if (taskState.isTimePickerStartOpen.value) {
                TimePickerDialogExample(
                    context,
                    taskState.isTimePickerStartOpen,
                    taskState.selectedTimeStartString
                )
            }
            if (taskState.isDatePickerStartOpen.value) {
                DatePickerDialogExample(
                    context,
                    taskState.isDatePickerStartOpen,
                    taskState.selectedDateStartString
                )
            }
            if (taskState.isTimePickerEndOpen.value) {
                TimePickerDialogExample(
                    context,
                    taskState.isTimePickerEndOpen,
                    taskState.selectedTimeEndString
                )
            }
            if(checkingValidationConditions(taskState)) {
                validationEndMoreThanStart(taskState, context)
//                val gson = Gson()
//                val serializableTaskState = taskState.toSerializableTaskState()
//                println(gson.toJson(serializableTaskState))
            }
            TextFiledWithTitle(messageTitleTask, taskState.titleTask)
            TextFiledWithTitle(messageDescriptionTask, taskState.descriptionTask)
            Button(
                onClick = {
                    val gson = Gson()
                    val serializableTaskState = taskState.toSerializableTaskState()
                    val task = Task(task = gson.toJson(serializableTaskState))
                    launchInsertTask(dataBase, task)
                }
            ) {
                Text("Save")
            }
            Button(
                onClick = {
                    launchPrintTasks(dataBase)
                }
            ) {
                Text("Get")
            }
        }
    }
}