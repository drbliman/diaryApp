package com.example.diaryapp.layout.screens.tasklist

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.diaryapp.layout.components.Calendar
import com.example.diaryapp.layout.screens.tasklist.components.TaskList
import com.example.diaryapp.layout.components.FloatingButtonExample
import androidx.navigation.NavController
import com.example.diaryapp.data.AppDatabase
import com.example.diaryapp.data.utils.launchGetTasks
import com.example.diaryapp.navigation.Routes
import com.example.diaryapp.utils.SerializableTaskState
import kotlinx.coroutines.launch
import java.time.LocalDate

@Composable
fun ScreenTaskList(navController: NavController, dataBase: AppDatabase) {
    val selectedDate = remember { mutableStateOf(LocalDate.now().toString()) }
    val tasks = remember { mutableStateOf(emptyList<SerializableTaskState>()) }

    LaunchedEffect(selectedDate.value) {
        kotlinx.coroutines.GlobalScope.launch {
            tasks.value = launchGetTasks(dataBase)
            println(tasks.value)
        }
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(paddingValues),
        ) {
            Calendar(paddingValues = PaddingValues(0.dp), selectedDate)
            TaskList(
                paddingValues = PaddingValues(0.dp),
                selectedDate,
                tasks,
            )
        }
        FloatingButtonExample(
            onClick = {
                navController.navigate(Routes.CREATE_TASK)
            }
        )
    }
}
