package com.example.diaryapp.layout.screens.createtask.components

import android.app.DatePickerDialog
import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.remember
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

@Composable
fun DatePickerDialogExample(
    context: Context,
    isDatePickerOpen: MutableState<Boolean>,
    selectedDate: MutableState<String>
) {
    val calendar = remember { Calendar.getInstance() }
    val dialog = DatePickerDialog(
        context,
        { _, year, month, dayOfMonth ->
            calendar.set(Calendar.YEAR, year)
            calendar.set(Calendar.MONTH, month)
            calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth)
            isDatePickerOpen.value = false
            val dateTimeFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
            selectedDate.value = dateTimeFormat.format(calendar.time)
        },
        calendar.get(Calendar.YEAR),
        calendar.get(Calendar.MONTH),
        calendar.get(Calendar.DAY_OF_MONTH)
    )

    dialog.setOnCancelListener {
        isDatePickerOpen.value = false
    }

    dialog.show()
}