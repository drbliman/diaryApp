package com.example.diaryapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.room.Room
import com.example.diaryapp.data.AppDatabase
import com.example.diaryapp.layout.screens.createtask.ScreenCreateTask
import com.example.diaryapp.navigation.Routes
import com.example.diaryapp.layout.screens.tasklist.ScreenTaskList

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val dataBase = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java,
            "app_database"
        )
            .fallbackToDestructiveMigration()
            .build()
        enableEdgeToEdge()
        setContent {
            val navController = rememberNavController()
            NavHost(
                navController = navController,
                startDestination = Routes.TASK_LIST,
            ) {
                composable(Routes.TASK_LIST) {
                    ScreenTaskList(navController, dataBase)
                }
                composable(Routes.CREATE_TASK) {
                    ScreenCreateTask(navController, dataBase)
                }
            }
        }
    }
}