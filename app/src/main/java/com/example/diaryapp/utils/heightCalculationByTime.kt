package com.example.diaryapp.utils

import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

fun heightCalculationByTime(item: SerializableTaskState): Dp {
    val duration = (item.selectedDateEndTimestamp - item.selectedDateStartTimestamp) / 1000
    val heightOfParentContainer = 100.dp
    val height = (duration * heightOfParentContainer.value) / 3600
    return height.dp
}