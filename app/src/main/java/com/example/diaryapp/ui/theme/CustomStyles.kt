package com.example.diaryapp.ui.theme

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.diaryapp.R

val customFontFamily = FontFamily(
    Font(R.font.montserrat)
)

val customTextColor = Color(0xFF000000)
val greenBG = Color(0xFFE0ECDE)

val baseTextStyle = TextStyle(
    fontFamily = customFontFamily,
    fontSize = 16.sp,
    color = customTextColor,
)

val customTextStyleTimeList = baseTextStyle.copy(
    textAlign = TextAlign.Center,
)

val customTextStyle = baseTextStyle.copy(
    textAlign = TextAlign.Start,
)

fun Modifier.customTextElementStyle() = this
    .background(greenBG, shape = RoundedCornerShape(12.dp))
    .padding(start = 8.dp)