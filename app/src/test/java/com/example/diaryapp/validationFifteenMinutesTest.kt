package com.example.diaryapp

import com.example.diaryapp.utils.TaskState
import com.example.diaryapp.utils.validation.validationFifteenMinutes
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test
import java.sql.Timestamp

class ValidationFifteenMinutesTest {

    @Test
    fun `validationFifteenMinutes returns true for difference greater than to 15 minutes`() {
        val taskState = TaskState().apply {
            selectedDateStartTimestamp.value = Timestamp(0)
            selectedDateEndTimestamp.value = Timestamp(40 * 60 * 1000)
        }

        val result = validationFifteenMinutes(taskState)

        assertTrue(result)
        assertTrue(taskState.validationFifteenMinutes.value)
    }

    @Test
    fun `validationFifteenMinutes returns false for difference less than 15 minutes`() {
        val taskState = TaskState().apply {
            selectedDateStartTimestamp.value = Timestamp(0)
            selectedDateEndTimestamp.value = Timestamp(14 * 60 * 1000)
        }

        val result = validationFifteenMinutes(taskState)

        assertFalse(result)
        assertFalse(taskState.validationFifteenMinutes.value)
    }

    @Test
    fun `validationFifteenMinutes returns true for exactly 15 minutes`() {
        val taskState = TaskState().apply {
            selectedDateStartTimestamp.value = Timestamp(0)
            selectedDateEndTimestamp.value = Timestamp(15 * 60 * 1000)
        }

        val result = validationFifteenMinutes(taskState)

        assertTrue(result)
        assertTrue(taskState.validationFifteenMinutes.value)
    }
}