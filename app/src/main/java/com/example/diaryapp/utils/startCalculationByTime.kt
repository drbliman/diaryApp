package com.example.diaryapp.utils

fun startCalculationByTime(time: String): Float {
    val hours = time.split(":")[0].toFloat()
    val minutes = time.split(":")[1].toFloat()
    return hours + minutes/60
}