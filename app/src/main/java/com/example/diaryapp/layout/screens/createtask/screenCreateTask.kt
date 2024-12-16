package com.example.diaryapp.layout.screens.createtask

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import com.example.diaryapp.R
import com.example.diaryapp.layout.components.TextFiledWithTitle
import com.example.diaryapp.layout.screens.createtask.components.DatePickerDialogExample
import com.example.diaryapp.layout.screens.createtask.components.TimePickerDialogExample

@Composable
fun ScreenCreateTask() {
    val context = LocalContext.current

    val isTimePickerOpen = remember { mutableStateOf(false) }
    val isDatePickerOpen = remember { mutableStateOf(false) }

    val selectedTime = remember { mutableStateOf("") }
    val selectedDate = remember { mutableStateOf("") }

    val titleTask = remember { mutableStateOf("") }
    val descriptionTask = remember { mutableStateOf("") }

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
            Button(
                onClick = { isTimePickerOpen.value = !isTimePickerOpen.value }
            ) {
                Text(selectedTime.value.ifEmpty { "Pick Time" })
            }
            Button(
                onClick = { isDatePickerOpen.value = !isDatePickerOpen.value }
            ) {
                Text(selectedDate.value.ifEmpty { "Pick Date" })
            }
            if (isTimePickerOpen.value) {
                TimePickerDialogExample(context, isTimePickerOpen, selectedTime)
            }
            if (isDatePickerOpen.value) {
                DatePickerDialogExample(context, isDatePickerOpen, selectedDate)
            }
            TextFiledWithTitle(messageTitleTask, titleTask)
            TextFiledWithTitle(messageDescriptionTask, descriptionTask)
        }
    }
}