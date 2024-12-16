package com.example.diaryapp.layout.screens.createtask

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
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
import androidx.compose.ui.unit.dp
import com.example.diaryapp.layout.calendar.Calendar
import androidx.compose.ui.platform.LocalContext
import com.example.diaryapp.layout.screens.createtask.components.TimePickerDialogExample

@Composable
fun ScreenCreateTask() {
    val context = LocalContext.current

    val isTimePickerOpen = remember { mutableStateOf(false) }
    val isDatePickerOpen = remember { mutableStateOf(false) }
    val selectedTime = remember { mutableStateOf("") }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(paddingValues),
        ) {
            Calendar(paddingValues = PaddingValues(0.dp))
            Button(
                onClick = { isTimePickerOpen.value = !isTimePickerOpen.value }
            ) {
                Text(selectedTime.value.ifEmpty { "Pick Time" })
            }
            if (isTimePickerOpen.value) {
                TimePickerDialogExample(context, isTimePickerOpen, isDatePickerOpen, selectedTime)
            }
        }
    }
}