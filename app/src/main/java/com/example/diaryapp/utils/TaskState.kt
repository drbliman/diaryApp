package com.example.diaryapp.utils

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf

class TaskState {
    val isTimePickerStartOpen: MutableState<Boolean> = mutableStateOf(false)
    val isDatePickerStartOpen: MutableState<Boolean> = mutableStateOf(false)

    val isTimePickerEndOpen: MutableState<Boolean> = mutableStateOf(false)
    val isDatePickerEndOpen: MutableState<Boolean> = mutableStateOf(false)

    val selectedTimeStartString: MutableState<String> = mutableStateOf("")
    val selectedDateStartString: MutableState<String> = mutableStateOf("")

    val selectedTimeEndString: MutableState<String> = mutableStateOf("")
    val selectedDateEndString: MutableState<String> = mutableStateOf("")

    val titleTask: MutableState<String> = mutableStateOf("")
    val descriptionTask: MutableState<String> = mutableStateOf("")
}