package com.example.diaryapp.layout.screens.tasklist.components

import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.diaryapp.ui.theme.customTextStyleTimeList

@Composable
fun TextTime(time: String) {
    Text(
        text = time,
        modifier = Modifier
            .width(60.dp),
        style = customTextStyleTimeList,
    )
}