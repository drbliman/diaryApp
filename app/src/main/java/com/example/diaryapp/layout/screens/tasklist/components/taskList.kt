package com.example.diaryapp.layout.screens.tasklist.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import java.time.LocalTime
import java.time.format.DateTimeFormatter
import java.util.Locale
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.MutableState
import androidx.compose.ui.graphics.Color
import com.example.diaryapp.utils.SerializableTaskState
import com.example.diaryapp.utils.filterTasksByDate

@Composable
fun TaskList(
    paddingValues : PaddingValues,
    selectedDate: MutableState<String>,
    tasks: MutableState<List<SerializableTaskState>>
) {
    val startTime = LocalTime.of(0, 0)

    val formatter = DateTimeFormatter.ofPattern("HH:mm", Locale.getDefault())

    val timeIntervals = List(24) { index -> startTime.plusMinutes(60L * index) }

    val myColor = Color(red = 0xF1, green = 0xAA, blue = 0x55, alpha = 0xFF)

    val filteredTasks = filterTasksByDate(selectedDate, tasks)

    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
            .padding(paddingValues)
            .background(myColor)
    ) {
        items(timeIntervals) { time ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp)
            ) {
                Text(
                    text = time.format(formatter),
                    modifier = Modifier.weight(1f),
                )
                filteredTasks.filter {
                    it.selectedTimeStartString.take(2) == time.format(formatter).take(2)
                }.forEach { item ->
                    Text(
                        text = "Task: ${item.titleTask}",
                        modifier = Modifier.padding(start = 8.dp)
                    )
                }
            }
        }
    }
}