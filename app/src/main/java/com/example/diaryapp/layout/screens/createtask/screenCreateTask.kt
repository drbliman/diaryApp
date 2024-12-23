package com.example.diaryapp.layout.screens.createtask

import android.widget.Toast
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.diaryapp.R
import com.example.diaryapp.data.AppDatabase
import com.example.diaryapp.data.Task
import com.example.diaryapp.data.utils.launchInsertTask
import com.example.diaryapp.layout.components.TextFiledWithTitle
import com.example.diaryapp.layout.screens.createtask.components.ButtonTimePicker
import com.example.diaryapp.layout.screens.createtask.components.DatePickerDialogExample
import com.example.diaryapp.layout.screens.createtask.components.PickerType
import com.example.diaryapp.layout.screens.createtask.components.TimePickerDialogExample
import com.example.diaryapp.ui.theme.baseTextStyle
import com.example.diaryapp.utils.TaskState
import com.example.diaryapp.utils.toSerializableTaskState
import com.example.diaryapp.utils.validation.checkingValidationConditions
import com.example.diaryapp.utils.validation.createTimestamp
import com.example.diaryapp.utils.validation.validation
import com.google.gson.Gson
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

@Composable
fun ScreenCreateTask(navController: NavController, dataBase: AppDatabase) {
    val context = LocalContext.current

    val taskState = remember { TaskState() }

    val messageTitleTask = context.getString(R.string.title_task)
    val messageDescriptionTask = context.getString(R.string.description_task)
    val messageSavedSuccessfully = context.getString(R.string.saved_successfully)
    val messageSavedFailed = context.getString(R.string.saved_failed)

    val focusManager = LocalFocusManager.current
    val keyboardController = LocalSoftwareKeyboardController.current
    Box(
        modifier = Modifier
            .verticalScroll(ScrollState(0))
            .fillMaxSize()
            .pointerInput(Unit) {
                detectTapGestures(
                    onTap = {
                        focusManager.clearFocus()
                        keyboardController?.hide()
                    }
                )
            },
        contentAlignment = Alignment.Center
    ) {
        Scaffold(
            modifier = Modifier
                .width(300.dp)
                .heightIn(min = 300.dp, max = 800.dp),
        ) { paddingValues ->
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(paddingValues),
                verticalArrangement = Arrangement.spacedBy(20.dp)
            ) {
                ButtonTimePicker(taskState, PickerType.DATE_START,
                    modifier = Modifier.fillMaxWidth())
                ButtonTimePicker(taskState, PickerType.TIME_START,
                    modifier = Modifier.fillMaxWidth())
                ButtonTimePicker(taskState, PickerType.TIME_END,
                    modifier = Modifier.fillMaxWidth())
                if (taskState.isTimePickerStartOpen.value) {
                    TimePickerDialogExample(
                        context,
                        taskState.isTimePickerStartOpen,
                        taskState.selectedTimeStartString
                    )
                }
                if (taskState.isDatePickerStartOpen.value) {
                    DatePickerDialogExample(
                        context,
                        taskState.isDatePickerStartOpen,
                        taskState.selectedDateStartString
                    )
                }
                if (taskState.isTimePickerEndOpen.value) {
                    TimePickerDialogExample(
                        context,
                        taskState.isTimePickerEndOpen,
                        taskState.selectedTimeEndString
                    )
                }
                LaunchedEffect(
                    taskState.isTimePickerEndOpen.value,
                    taskState.isTimePickerStartOpen.value,
                    taskState.isDatePickerStartOpen.value) {
                    if (checkingValidationConditions(taskState)) {
                        createTimestamp(taskState)
                        CoroutineScope(Dispatchers.Main).launch {
                            validation(taskState, dataBase)
                        }
                    }
                }
                TextFiledWithTitle(messageTitleTask, taskState.titleTask)
                TextFiledWithTitle(messageDescriptionTask, taskState.descriptionTask)
                Button(
                    enabled = taskState.validation.value,
                    onClick = {
                        val gson = Gson()
                        val serializableTaskState = taskState.toSerializableTaskState()
                        val task = Task(task = gson.toJson(serializableTaskState))
                        GlobalScope.launch(Dispatchers.Main) {
                            val isSuccess = launchInsertTask(dataBase, task)
                            if (isSuccess) {
                                Toast.makeText(context, messageSavedSuccessfully, Toast.LENGTH_SHORT).show()
                                navController.popBackStack()
                            } else {
                                Toast.makeText(context, messageSavedFailed, Toast.LENGTH_SHORT).show()
                            }
                        }
                    },
                    colors = ButtonDefaults.buttonColors(
                        contentColor = Color(0xff004D40),
                        containerColor = Color(0xffE0ECDE),
                    ),
                    modifier = Modifier
                        .fillMaxWidth(),
                ) {
                    Text("Save")
                }
                if (
                    !taskState.validation.value &&
                    taskState.selectedTimeStartString.value.trim().isNotEmpty() &&
                    taskState.selectedDateStartString.value.trim().isNotEmpty() &&
                    taskState.selectedTimeEndString.value.trim().isNotEmpty()
                    ) {
                    var text = ""
                    if (!taskState.validationConflictOfTasks.value) {
                        text = context.getString(R.string.validation_conflict_of_tasks)
                    } else if (!taskState.validationEndMoreThanStart.value) {
                        text = context.getString(R.string.validation_end_equals_start)
                    } else if (!taskState.validationFifteenMinutes.value) {
                        text = context.getString(R.string.validation_fifteen_minutes)
                    }
                    Text(
                        text = text,
                        style = baseTextStyle,
                        color = Color.Red
                    )
                }
            }
        }
    }
}