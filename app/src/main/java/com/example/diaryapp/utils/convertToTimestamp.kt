package com.example.diaryapp.utils

import java.sql.Timestamp
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

fun convertToTimestamp(date: String, time: String): Timestamp {
    val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")

    val localDateTime = LocalDateTime.parse("$date $time", formatter)

    val fullDateTimeString = localDateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))

    return Timestamp.valueOf(fullDateTimeString)
}