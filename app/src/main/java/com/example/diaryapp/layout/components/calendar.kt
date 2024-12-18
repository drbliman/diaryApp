package com.example.diaryapp.layout.components

import android.widget.CalendarView
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView

@Composable
fun Calendar(paddingValues : PaddingValues, selectedDate: MutableState<String>) {
    AndroidView(
        modifier = Modifier
            .fillMaxWidth()
            .padding(paddingValues),
        factory = { context ->
            CalendarView(context).apply {
            }
        },
        update = { calendarView ->
            calendarView.setOnDateChangeListener { _, year, month, dayOfMonth ->
                selectedDate.value = "$year-${month + 1}-$dayOfMonth"
            }
        }
    )
}