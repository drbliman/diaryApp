package com.example.diaryapp.layout.screens.tasklist

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.diaryapp.layout.calendar.Calendar
import com.example.diaryapp.layout.components.TaskList

@Composable
fun ScreenTaskList() {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(paddingValues),
        ) {
            Calendar(paddingValues = PaddingValues(0.dp))
            TaskList(
                paddingValues = PaddingValues(0.dp),
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth()
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ScreenTaskList()
}