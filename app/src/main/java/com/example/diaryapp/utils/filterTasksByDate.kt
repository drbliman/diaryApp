package com.example.diaryapp.utils

import androidx.compose.runtime.MutableState

fun filterTasksByDate(
    selectedDate: MutableState<String>,
    tasks: MutableState<List<SerializableTaskState>>
): List<SerializableTaskState> {
    return tasks.value.filter { it.selectedDateStartString == selectedDate.value }
}