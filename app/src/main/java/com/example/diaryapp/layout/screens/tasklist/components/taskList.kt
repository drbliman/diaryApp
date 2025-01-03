package com.example.diaryapp.layout.screens.tasklist.components

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.example.diaryapp.R
import com.example.diaryapp.data.AppDatabase
import com.example.diaryapp.ui.theme.customTextElementStyle
import com.example.diaryapp.utils.SerializableTaskState
import com.example.diaryapp.utils.calculateWidth
import com.example.diaryapp.utils.filterTasksByDate
import com.example.diaryapp.utils.heightCalculationByTime
import com.example.diaryapp.utils.startCalculationByTime
import java.time.LocalTime
import java.time.format.DateTimeFormatter
import java.util.Locale


@SuppressLint("ResourceAsColor")
@Composable
fun TaskList(
    paddingValues : PaddingValues,
    selectedDate: MutableState<String>,
    tasks: MutableState<List<SerializableTaskState>>,
    dataBase: AppDatabase,
) {
    val context = LocalContext.current
    val startTime = LocalTime.of(0, 0)
    val formatter = DateTimeFormatter.ofPattern("HH:mm", Locale.getDefault())
    val timeIntervals = List(24) { index -> startTime.plusMinutes(60L * index) }
    val filteredTasks = filterTasksByDate(selectedDate, tasks)
    val messageDeleteSuccessfully = context.getString(R.string.delete_successfully)
    val messageDeleteFailed = context.getString(R.string.delete_failed)

    ConstraintLayout(
        modifier = Modifier
            .fillMaxWidth()
            .padding(paddingValues)
            .verticalScroll(rememberScrollState())
    ) {
        val boxes = Array(24) { createRef() }
        val boxesTask = Array(filteredTasks.size) { createRef() }

        boxes.forEachIndexed { index, ref ->
            Box(
                modifier = Modifier
                    .constrainAs(ref) {
                        start.linkTo(parent.start, margin = 10.dp)
                        top.linkTo(parent.top, margin = (index * 110).dp)
                    }
                    .height(100.dp),
            ) {
                TextTime(timeIntervals[index].format(formatter))
            }
        }

        val width = calculateWidth()

        filteredTasks.forEachIndexed { index, item ->
            val start = startCalculationByTime(item.selectedTimeStartString)
            val taskDialogState = remember { mutableStateOf(false) }

            Box(
                modifier = Modifier
                    .constrainAs(boxesTask[index]) {
                        end.linkTo(parent.end, margin = 10.dp)
                        top.linkTo(parent.top, margin = (start * 110).dp)
                    }
                    .height(heightCalculationByTime(item))
                    .width(width.dp)
                    .customTextElementStyle()
                    .padding(end = 15.dp),
            ) {
                TextTask(item)
                IconButton(
                    onClick = { taskDialogState.value = true },
                    modifier = Modifier
                        .padding(top = 4.dp)
                        .align(Alignment.TopEnd)
                        .height(20.dp)
                        .width(20.dp)
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.info),
                        contentDescription = "arrow_info"
                    )
                }
            }
            if (taskDialogState.value) {
                TaskAlertDialog(
                    taskDialogState = taskDialogState,
                    item = item,
                    tasks = tasks,
                    dataBase = dataBase,
                    context = context,
                    messageDeleteSuccessfully = messageDeleteSuccessfully,
                    messageDeleteFailed = messageDeleteFailed
                )
            }
        }
    }
}