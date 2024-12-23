package com.example.diaryapp.utils

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import java.sql.Timestamp

class TaskState {
    val isTimePickerStartOpen: MutableState<Boolean> = mutableStateOf(false)
    val isTimePickerEndOpen: MutableState<Boolean> = mutableStateOf(false)
    val isDatePickerStartOpen: MutableState<Boolean> = mutableStateOf(false)

    val validation: MutableState<Boolean> = mutableStateOf(false)
    val validationConflictOfTasks: MutableState<Boolean> = mutableStateOf(false)
    val validationEndMoreThanStart: MutableState<Boolean> = mutableStateOf(false)
    val validationFifteenMinutes: MutableState<Boolean> = mutableStateOf(false)

    val selectedTimeStartString: MutableState<String> = mutableStateOf("")
    val selectedTimeEndString: MutableState<String> = mutableStateOf("")
    val selectedDateStartString: MutableState<String> = mutableStateOf("")

    val selectedDateStartTimestamp: MutableState<Timestamp> = mutableStateOf(Timestamp(0))
    val selectedDateEndTimestamp: MutableState<Timestamp> = mutableStateOf(Timestamp(0))

    val titleTask: MutableState<String> = mutableStateOf("")
    val descriptionTask: MutableState<String> = mutableStateOf("")
}