package com.example.diaryapp.layout.screens.createtask.components

import android.annotation.SuppressLint
import android.app.TimePickerDialog
import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState

@SuppressLint("DefaultLocale")
@Composable
fun TimePickerDialogExample(
    context: Context,
    isTimePickerOpen: MutableState<Boolean>,
    selectedTime: MutableState<String>
) {
    val dialog = TimePickerDialog(
        context,
        { _, hourOfDay, minute ->
            selectedTime.value = String.format("%02d:%02d", hourOfDay, minute)
            isTimePickerOpen.value = false
        },
        12,
        0,
        true
    )

    dialog.setOnCancelListener {
        isTimePickerOpen.value = false
    }

    dialog.show()
}