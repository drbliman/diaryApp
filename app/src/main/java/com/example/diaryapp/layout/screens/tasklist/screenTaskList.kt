package com.example.diaryapp.layout.screens.tasklist

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.diaryapp.layout.calendar.Calendar
import com.example.diaryapp.layout.screens.tasklist.components.TaskList
import com.example.diaryapp.layout.components.FloatingButtonExample
import androidx.navigation.NavController
import com.example.diaryapp.navigation.Routes

@Composable
fun ScreenTaskList(navController: NavController) {
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
        FloatingButtonExample(
            onClick = {
                navController.navigate(Routes.CREATE_TASK)
            }
        )
    }
}