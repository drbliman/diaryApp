package com.example.diaryapp.layout.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier

@Composable
fun TextFiledWithTitle(title: String) {
    val message = remember{ mutableStateOf("") }
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