package com.example.diaryapp.layout.screens.createtask

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
import androidx.compose.ui.platform.LocalContext
import com.example.diaryapp.R
import com.example.diaryapp.layout.components.TextFiledWithTitle
import com.example.diaryapp.layout.screens.createtask.components.ButtonTimePicker
import com.example.diaryapp.layout.screens.createtask.components.DatePickerDialogExample
import com.example.diaryapp.layout.screens.createtask.components.PickerType
import com.example.diaryapp.layout.screens.createtask.components.TimePickerDialogExample
import com.example.diaryapp.utils.TaskState

@Composable
fun ScreenCreateTask() {
    val context = LocalContext.current

    val taskState = remember { TaskState() }

    val messageTitleTask = context.getString(R.string.title_task)
    val messageDescriptionTask = context.getString(R.string.description_task)

    Scaffold(
        modifier = Modifier.fillMaxSize(),
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(paddingValues),
        ) {
//            Button(
//                onClick = { taskState.isTimePickerStartOpen.value = !taskState.isTimePickerStartOpen.value }
//            ) {
//                Text(taskState.selectedTimeStartString.value.ifEmpty { "Pick Time" })
//            }
//            Button(
//                onClick = { taskState.isDatePickerStartOpen.value = !taskState.isDatePickerStartOpen.value }
//            ) {
//                Text(taskState.selectedDateStartString.value.ifEmpty { "Pick Date" })
//            }
            ButtonTimePicker(taskState, PickerType.DATE_START)
            ButtonTimePicker(taskState, PickerType.TIME_START)
            ButtonTimePicker(taskState, PickerType.DATE_END)
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
            TextFiledWithTitle(messageTitleTask, taskState.titleTask)
            TextFiledWithTitle(messageDescriptionTask, taskState.descriptionTask)
        }
    }
}