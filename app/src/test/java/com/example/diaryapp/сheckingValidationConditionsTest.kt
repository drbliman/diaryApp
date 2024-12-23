package com.example.diaryapp

import com.example.diaryapp.utils.TaskState
import com.example.diaryapp.utils.validation.checkingValidationConditions
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

class CheckingValidationConditionsTest {

    @Test
    fun `checkingValidationConditions returns true when all conditions are met`() {
        val taskState = TaskState().apply {
            selectedTimeStartString.value = "10:00"
            selectedDateStartString.value = "2024-12-21"
            selectedTimeEndString.value = "12:00"
            isTimePickerEndOpen.value = false
            isTimePickerStartOpen.value = false
            isDatePickerStartOpen.value = false
        }

        val result = checkingValidationConditions(taskState)
        assertTrue(result)
    }

    @Test
    fun `checkingValidationConditions returns false when selectedTimeStartString is empty`() {
        val taskState = TaskState().apply {
            selectedTimeStartString.value = ""
            selectedDateStartString.value = "2024-12-21"
            selectedTimeEndString.value = "12:00"
            isTimePickerEndOpen.value = false
            isTimePickerStartOpen.value = false
            isDatePickerStartOpen.value = false
        }

        val result = checkingValidationConditions(taskState)
        assertFalse(result)
    }

    @Test
    fun `checkingValidationConditions returns false when selectedDateStartString is empty`() {
        val taskState = TaskState().apply {
            selectedTimeStartString.value = "10:00"
            selectedDateStartString.value = ""
            selectedTimeEndString.value = "12:00"
            isTimePickerEndOpen.value = false
            isTimePickerStartOpen.value = false
            isDatePickerStartOpen.value = false
        }

        val result = checkingValidationConditions(taskState)
        assertFalse(result)
    }

    @Test
    fun `checkingValidationConditions returns false when selectedTimeEndString is empty`() {
        val taskState = TaskState().apply {
            selectedTimeStartString.value = "10:00"
            selectedDateStartString.value = "2024-12-21"
            selectedTimeEndString.value = ""
            isTimePickerEndOpen.value = false
            isTimePickerStartOpen.value = false
            isDatePickerStartOpen.value = false
        }

        val result = checkingValidationConditions(taskState)
        assertFalse(result)
    }

    @Test
    fun `checkingValidationConditions returns false when a picker1 is open`() {
        val taskState = TaskState().apply {
            selectedTimeStartString.value = "10:00"
            selectedDateStartString.value = "2024-12-21"
            selectedTimeEndString.value = "12:00"
            isTimePickerEndOpen.value = true
            isTimePickerStartOpen.value = false
            isDatePickerStartOpen.value = false
        }

        val result = checkingValidationConditions(taskState)
        assertFalse(result)
    }

    @Test
    fun `checkingValidationConditions returns false when a picker2 is open`() {
        val taskState = TaskState().apply {
            selectedTimeStartString.value = "10:00"
            selectedDateStartString.value = "2024-12-21"
            selectedTimeEndString.value = "12:00"
            isTimePickerEndOpen.value = false
            isTimePickerStartOpen.value = true
            isDatePickerStartOpen.value = false
        }

        val result = checkingValidationConditions(taskState)
        assertFalse(result)
    }

    @Test
    fun `checkingValidationConditions returns false when a picker3 is open`() {
        val taskState = TaskState().apply {
            selectedTimeStartString.value = "10:00"
            selectedDateStartString.value = "2024-12-21"
            selectedTimeEndString.value = "12:00"
            isTimePickerEndOpen.value = true
            isTimePickerStartOpen.value = true
            isDatePickerStartOpen.value = false
        }

        val result = checkingValidationConditions(taskState)
        assertFalse(result)
    }
}