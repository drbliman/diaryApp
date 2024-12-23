package com.example.diaryapp

import androidx.compose.ui.unit.dp
import com.example.diaryapp.utils.SerializableTaskState
import com.example.diaryapp.utils.heightCalculationByTime
import org.junit.Assert.assertEquals
import org.junit.Test

class HeightCalculationByTimeTest {

    @Test
    fun `heightCalculationByTime returns correct height for 1 hour duration`() {
        val item = SerializableTaskState(
            selectedDateStartTimestamp = 0L,
            selectedDateEndTimestamp = 3600_000L,
            descriptionTask = "",
            selectedTimeEndString = "",
            selectedTimeStartString = "",
            selectedDateStartString = "",
            titleTask = "",
        )

        val expectedHeight = 108.dp
        val result = heightCalculationByTime(item)

        assertEquals(expectedHeight, result)
    }

    @Test
    fun `heightCalculationByTime returns correct height for 30 minutes duration`() {
        val item = SerializableTaskState(
            selectedDateStartTimestamp = 0L,
            selectedDateEndTimestamp = 1800_000L,
            descriptionTask = "",
            selectedTimeEndString = "",
            selectedTimeStartString = "",
            selectedDateStartString = "",
            titleTask = "",
        )

        val expectedHeight = 54.dp
        val result = heightCalculationByTime(item)

        assertEquals(expectedHeight, result)
    }

    @Test
    fun `heightCalculationByTime returns correct height for 2 hours duration`() {
        val item = SerializableTaskState(
            selectedDateStartTimestamp = 0L,
            selectedDateEndTimestamp = 7200_000L,
            descriptionTask = "",
            selectedTimeEndString = "",
            selectedTimeStartString = "",
            selectedDateStartString = "",
            titleTask = "",
        )

        val expectedHeight = 216.dp
        val result = heightCalculationByTime(item)

        assertEquals(expectedHeight, result)
    }

    @Test
    fun `heightCalculationByTime returns zero height for zero duration`() {
        val item = SerializableTaskState(
            selectedDateStartTimestamp = 0L,
            selectedDateEndTimestamp = 0L,
            descriptionTask = "",
            selectedTimeEndString = "",
            selectedTimeStartString = "",
            selectedDateStartString = "",
            titleTask = "",
        )

        val expectedHeight = 0.dp
        val result = heightCalculationByTime(item)

        assertEquals(expectedHeight, result)
    }
}