package com.example.diaryapp.layout.screens.tasklist.components

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import java.time.LocalTime
import java.time.format.DateTimeFormatter
import java.util.Locale
import androidx.compose.runtime.MutableState
import com.example.diaryapp.utils.SerializableTaskState
import com.example.diaryapp.utils.filterTasksByDate
import com.example.diaryapp.ui.theme.customTextStyleTimeList
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.constraintlayout.compose.ConstraintLayout
import com.example.diaryapp.ui.theme.customTextElementStyle
import com.example.diaryapp.ui.theme.customTextStyle
import com.example.diaryapp.utils.calculateWidth
import com.example.diaryapp.utils.heightCalculationByTime
import com.example.diaryapp.utils.startCalculationByTime

@SuppressLint("ResourceAsColor")
@Composable
fun TaskList(
    paddingValues : PaddingValues,
    selectedDate: MutableState<String>,
    tasks: MutableState<List<SerializableTaskState>>,
) {
    val startTime = LocalTime.of(0, 0)

    val formatter = DateTimeFormatter.ofPattern("HH:mm", Locale.getDefault())

    val timeIntervals = List(24) { index -> startTime.plusMinutes(60L * index) }

    val filteredTasks = filterTasksByDate(selectedDate, tasks)

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
                Text(
                    text = timeIntervals[index].format(formatter),
                    modifier = Modifier
                        .width(60.dp),
                    style = customTextStyleTimeList,
                )
            }
        }

        val width = calculateWidth()

        filteredTasks.forEachIndexed { index, item ->
            val start = startCalculationByTime(item.selectedTimeStartString)
            println(start)
            Box(
                modifier = Modifier
                    .constrainAs(boxesTask[index]) {
                        end.linkTo(parent.end, margin = 10.dp)
                        top.linkTo(parent.top, margin = (start * 110).dp)
                    }
                    .height(heightCalculationByTime(item))
                    .width(width.dp)
                    .customTextElementStyle(),
            ) {
                Text(
                        text = "Task: ${item.titleTask}",
                        modifier = Modifier
                            .customTextElementStyle()
                            .height(heightCalculationByTime(item)),
                        style = customTextStyle,
                    )
            }
        }
    }
}