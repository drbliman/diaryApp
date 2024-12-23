package com.example.diaryapp.layout.screens.createtask.components

import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.example.diaryapp.utils.TaskState

@Composable
fun ButtonTimePicker(taskState: TaskState, dataTimeType: PickerType, modifier: Modifier) {
    Button(
        modifier= modifier,
        onClick = {
            when (dataTimeType) {
                PickerType.DATE_START ->
                    taskState.isDatePickerStartOpen.value = !taskState.isDatePickerStartOpen.value
                PickerType.TIME_START ->
                    taskState.isTimePickerStartOpen.value = !taskState.isTimePickerStartOpen.value
                PickerType.TIME_END ->
                    taskState.isTimePickerEndOpen.value = !taskState.isTimePickerEndOpen.value
            }
        },
        colors = ButtonDefaults.buttonColors(
            contentColor = Color(0xff004D40),
            containerColor = Color(0xffE0ECDE),
        )
    ) {
        Text(
            when (dataTimeType) {
                PickerType.DATE_START ->
                    taskState.selectedDateStartString.value.ifEmpty { "Pick date" }
                PickerType.TIME_START ->
                    taskState.selectedTimeStartString.value.ifEmpty { "Pick time start" }
                PickerType.TIME_END ->
                    taskState.selectedTimeEndString.value.ifEmpty { "Pick time end" }
            }
        )
    }
}

enum class PickerType {
    DATE_START, TIME_START, TIME_END
}