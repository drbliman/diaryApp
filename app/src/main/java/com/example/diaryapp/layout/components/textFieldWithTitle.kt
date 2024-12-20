package com.example.diaryapp.layout.components

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.diaryapp.ui.theme.baseTextStyle

@Composable
fun TextFiledWithTitle(title: String, message: MutableState<String>) {
    Column(
        modifier = Modifier
            .fillMaxWidth(),
    ) {
        Text(
            title,
            style = baseTextStyle,
        )
        TextField(
            value = message.value,
            placeholder = {
                Text(
                    text = title,
                    style = baseTextStyle
                )
            },
            onValueChange = { newText ->
                if (title == "Title" && newText.length > 40) {
                    return@TextField
                }
                if (title == "Description" && newText.length > 350) {
                    return@TextField
                }
                message.value = newText
            },
            textStyle = baseTextStyle,
            modifier = Modifier
                .fillMaxWidth()
                .heightIn(min = 56.dp, max = 100.dp),
            colors = TextFieldDefaults.colors(
                unfocusedContainerColor = Color(0xffE0ECDE),
                focusedContainerColor = Color(0xffE0ECDE),
            )
        )
    }
}