package com.example.diaryapp.layout.screens.createtask.components

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.remember
import java.util.Calendar

@SuppressLint("DefaultLocale")
@Composable
fun TimePickerDialogExample(context: Context, isTimePickerOpen: MutableState<Boolean>, isDatePickerOpen: MutableState<Boolean>, selectedTime: MutableState<String>) {
//    val calendar = remember { Calendar.getInstance() }
//    DatePickerDialog(
//        context,
//        { _, year, month, dayOfMonth ->
//            calendar.set(Calendar.YEAR, year)
//            calendar.set(Calendar.MONTH, month)
//            calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth)
//            isDatePickerOpen.value = false
//            isTimePickerOpen.value = true
//        },
//        calendar.get(Calendar.YEAR),
//        calendar.get(Calendar.MONTH),
//        calendar.get(Calendar.DAY_OF_MONTH)
//    ).show()
    TimePickerDialog(
        context,
        { _, hourOfDay, minute ->
            selectedTime.value = String.format("%02d:%02d", hourOfDay, minute)
            isTimePickerOpen.value = false
        },
        12,
        0,
        true
    ).show()
}