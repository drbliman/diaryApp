package com.example.diaryapp.layout.screens.createtask.components

import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.example.diaryapp.utils.TaskState

@Composable
fun ButtonTimePicker(taskState: TaskState, dataTimeType: PickerType) {
    Button(
        onClick = {
            when (dataTimeType) {
                PickerType.DATE_START ->
                    taskState.isDatePickerStartOpen.value = !taskState.isDatePickerStartOpen.value
                PickerType.TIME_START ->
                    taskState.isTimePickerStartOpen.value = !taskState.isTimePickerStartOpen.value
                PickerType.TIME_END ->
                    taskState.isTimePickerEndOpen.value = !taskState.isTimePickerEndOpen.value
            }
        }
    ) {
        Text(
            when (dataTimeType) {
                PickerType.DATE_START ->
                    taskState.selectedDateStartString.value.ifEmpty { "Pick Date" }
                PickerType.TIME_START ->
                    taskState.selectedTimeStartString.value.ifEmpty { "Pick Time" }
                PickerType.TIME_END ->
                    taskState.selectedTimeEndString.value.ifEmpty { "Pick Time" }
            }
        )
    }
}

enum class PickerType {
    DATE_START, TIME_START, TIME_END
}