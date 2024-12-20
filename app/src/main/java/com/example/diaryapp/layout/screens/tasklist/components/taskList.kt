package com.example.diaryapp.layout.screens.tasklist.components

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import androidx.constraintlayout.compose.ConstraintLayout
import com.example.diaryapp.ui.theme.customTextElementStyle
import com.example.diaryapp.ui.theme.customTextStyle
import com.example.diaryapp.ui.theme.customTextStyleTimeList
import com.example.diaryapp.ui.theme.greenBG
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
) {
    val startTime = LocalTime.of(0, 0)
    val formatter = DateTimeFormatter.ofPattern("HH:mm", Locale.getDefault())
    val timeIntervals = List(24) { index -> startTime.plusMinutes(60L * index) }
    val filteredTasks = filterTasksByDate(selectedDate, tasks)
//    val currentTime = LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm"))

    ConstraintLayout(
        modifier = Modifier
            .fillMaxWidth()
            .padding(paddingValues)
            .verticalScroll(rememberScrollState())
    ) {
        val boxes = Array(24) { createRef() }
        val boxesTask = Array(filteredTasks.size) { createRef() }
        val redLineRef = createRef()

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
                        text =
                        "${item.selectedTimeStartString} - ${item.selectedTimeEndString} ${item.titleTask}\n${item.descriptionTask}",
                        modifier = Modifier
                            .customTextElementStyle()
                            .height(heightCalculationByTime(item))
                            .padding(5.dp),
                        style = customTextStyle,
                        maxLines = (heightCalculationByTime(item).value / customTextStyle.fontSize.value).toInt(),
                        overflow = TextOverflow.Ellipsis,
                    )
            }
        }

//        val startCurrentTime = startCalculationByTime(currentTime)
//        Box(
//            modifier = Modifier
//                .constrainAs(redLineRef) {
//                    start.linkTo(parent.start, margin = 0.dp)
//                    top.linkTo(parent.top, margin = (startCurrentTime * 110).dp)
//                }
//                .fillMaxWidth()
//                .height(1.dp)
//                .background(Color.Red)
//                .zIndex(2F),
//        )
    }
}