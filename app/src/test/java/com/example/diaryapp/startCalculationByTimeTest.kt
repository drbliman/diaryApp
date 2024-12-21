package com.example.diaryapp

import org.junit.Assert.assertEquals
import org.junit.Test
import com.example.diaryapp.utils.startCalculationByTime

class StartCalculationByTimeTest {

    @Test
    fun `startCalculationByTime returns correct float for valid time`() {
        val time = "14:30"
        val expected = 14.5f // 14 + 30/60
        val result = startCalculationByTime(time)

        assertEquals(expected, result, 0.001f)
    }

    @Test
    fun `startCalculationByTime returns correct value for time with zero minutes`() {
        val time = "10:00"
        val expected = 10.0f
        val result = startCalculationByTime(time)

        assertEquals(expected, result, 0.001f)
    }

    @Test
    fun `startCalculationByTime returns correct value for time with zero hours`() {
        val time = "00:45"
        val expected = 0.75f // 0 + 45/60
        val result = startCalculationByTime(time)

        assertEquals(expected, result, 0.001f)
    }

    @Test
    fun `startCalculationByTime returns correct value for midnight`() {
        val time = "00:00"
        val expected = 0.0f
        val result = startCalculationByTime(time)

        assertEquals(expected, result, 0.001f)
    }

    @Test(expected = IndexOutOfBoundsException::class)
    fun `startCalculationByTime throws exception for invalid time format`() {
        val time = "14" //
        startCalculationByTime(time)
    }

    @Test(expected = NumberFormatException::class)
    fun `startCalculationByTime throws exception for non-numeric input`() {
        val time = "hh:mm"
        startCalculationByTime(time)
    }
}