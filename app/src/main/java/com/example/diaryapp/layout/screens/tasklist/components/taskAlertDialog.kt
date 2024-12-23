package com.example.diaryapp.layout.screens.tasklist.components

import android.content.Context
import android.widget.Toast
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import com.example.diaryapp.data.AppDatabase
import com.example.diaryapp.data.utils.deleteTaskByIdAndTask
import com.example.diaryapp.data.utils.getTaskIdByTask
import com.example.diaryapp.utils.SerializableTaskState
import com.google.gson.Gson
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Composable
fun TaskAlertDialog(
    taskDialogState: MutableState<Boolean>,
    item: SerializableTaskState,
    tasks: MutableState<List<SerializableTaskState>>,
    dataBase: AppDatabase,
    context: Context,
    messageDeleteSuccessfully: String,
    messageDeleteFailed: String
) {
    val taskId = remember { mutableStateOf<Int?>(null) }
    val gson = Gson()

    LaunchedEffect(key1 = taskDialogState) {
        taskId.value = getTaskIdByTask(dataBase, gson.toJson(item))
    }

    AlertDialog(
        onDismissRequest = { taskDialogState.value = false },
        confirmButton = {
            TextButton(onClick = {
                val currentTaskId = taskId.value
                if (currentTaskId != null) {
                    CoroutineScope(Dispatchers.Main).launch {
                        val deletedTask = deleteTaskByIdAndTask(dataBase, currentTaskId, gson.toJson(item))
                        if (deletedTask != null) {
                            tasks.value = tasks.value.filter { it != item }
                            Toast.makeText(context, messageDeleteSuccessfully, Toast.LENGTH_SHORT).show()
                            taskDialogState.value = false
                        } else {
                            Toast.makeText(context, messageDeleteFailed, Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            }) {
                Text("Delete")
            }
            TextButton(onClick = { taskDialogState.value = false }) {
                Text("Close")
            }
        },
        title = {
            Text(text = "Task information")
        },
        text = {
            Text(
                text = "${item.selectedTimeStartString} - ${item.selectedTimeEndString}" +
                        "\n${item.titleTask}\n${item.descriptionTask}"
            )
        }
    )
}
