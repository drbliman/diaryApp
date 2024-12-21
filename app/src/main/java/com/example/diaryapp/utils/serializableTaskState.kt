package com.example.diaryapp.utils

data class SerializableTaskState(
    val selectedTimeStartString: String,
    val selectedDateStartString: String,
    val selectedTimeEndString: String,
    val selectedDateStartTimestamp: Long,
    val selectedDateEndTimestamp: Long,
    val titleTask: String,
    val descriptionTask: String,
)

fun TaskState.toSerializableTaskState(): SerializableTaskState {
    return SerializableTaskState(
        selectedTimeStartString = selectedTimeStartString.value,
        selectedDateStartString = selectedDateStartString.value,
        selectedTimeEndString = selectedTimeEndString.value,
        selectedDateStartTimestamp = selectedDateStartTimestamp.value.time,
        selectedDateEndTimestamp = selectedDateEndTimestamp.value.time,
        titleTask = titleTask.value,
        descriptionTask = descriptionTask.value,
    )
}