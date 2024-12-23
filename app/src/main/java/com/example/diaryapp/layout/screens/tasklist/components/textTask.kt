package com.example.diaryapp.layout.screens.tasklist.components

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.example.diaryapp.ui.theme.customTextElementStyle
import com.example.diaryapp.ui.theme.customTextStyle
import com.example.diaryapp.utils.SerializableTaskState
import com.example.diaryapp.utils.heightCalculationByTime

@Composable
fun TextTask(item: SerializableTaskState) {
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