package com.example.diaryapp.layout.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier

@Composable
fun TextFiledWithTitle(title: String, message: MutableState<String>) {
    Column(
        modifier = Modifier
            .fillMaxWidth(),
    ) {
        Text(title)
        TextField(
            value = message.value,
            placeholder = {
                Text(text = title)
            },
            onValueChange = { newText -> message.value = newText },
        )
    }
}