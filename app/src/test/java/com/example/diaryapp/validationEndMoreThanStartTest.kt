package com.example.diaryapp

import com.example.diaryapp.utils.TaskState
import com.example.diaryapp.utils.validation.validationEndMoreThanStart
import org.junit.Assert.*
import org.junit.Test
import java.sql.Timestamp

class validationEndMoreThanStartTest {
    @Test
    fun `validationEndMoreThanStart returns true when end is after start`() {
        val startDate = Timestamp.valueOf("2024-12-21 10:00:00")
        val endDate = Timestamp.valueOf("2024-12-21 11:00:00")

        val taskState = TaskState().apply {
            selectedDateStartTimestamp.value = startDate
            selectedDateEndTimestamp.value = endDate
        }

        val result = validationEndMoreThanStart(taskState)

        assertTrue("End date should be greater than start date", result)
        assertTrue("Validation flag should be true", taskState.validationEndMoreThanStart.value)
    }

    @Test
    fun `validationEndMoreThanStart returns false when end is before start`() {
        val startDate = Timestamp.valueOf("2024-12-21 12:00:00")
        val endDate = Timestamp.valueOf("2024-12-21 10:00:00")

        val taskState = TaskState().apply {
            selectedDateStartTimestamp.value = startDate
            selectedDateEndTimestamp.value = endDate
        }

        val result = validationEndMoreThanStart(taskState)

        assertFalse("End date should not be greater than start date", result)
        assertFalse("Validation flag should be false", taskState.validationEndMoreThanStart.value)
    }

    @Test
    fun `validationEndMoreThanStart returns false when end is the same as start`() {
        val startDate = Timestamp.valueOf("2024-12-21 10:00:00")
        val endDate = Timestamp.valueOf("2024-12-21 10:00:00")

        val taskState = TaskState().apply {
            selectedDateStartTimestamp.value = startDate
            selectedDateEndTimestamp.value = endDate
        }

        val result = validationEndMoreThanStart(taskState)

        assertFalse("End date should not be greater than start date", result)
        assertFalse("Validation flag should be false", taskState.validationEndMoreThanStart.value)
    }
}