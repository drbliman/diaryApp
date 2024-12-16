package com.example.diaryapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.diaryapp.layout.screens.createtask.ScreenCreateTask
import com.example.diaryapp.navigation.Routes
import com.example.diaryapp.layout.screens.tasklist.ScreenTaskList

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val navController = rememberNavController()
            NavHost(
                navController = navController,
                startDestination = Routes.TASK_LIST,
            ) {
                composable(Routes.TASK_LIST) {
                    ScreenTaskList(navController)
                }
                composable(Routes.CREATE_TASK) {
                    ScreenCreateTask()
                }
            }
        }
    }
}