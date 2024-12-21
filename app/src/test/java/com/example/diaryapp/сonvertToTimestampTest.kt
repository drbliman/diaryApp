package com.example.diaryapp

import org.junit.Assert.assertEquals
import org.junit.Assert.assertThrows
import org.junit.Test
import java.sql.Timestamp
import java.time.format.DateTimeParseException
import com.example.diaryapp.utils.convertToTimestamp

class ConvertToTimestampTest {

    @Test
    fun `convertToTimestamp returns correct Timestamp for valid inputs`() {
        val date = "2024-12-21"
        val time = "14:30"

        val expectedTimestamp = Timestamp.valueOf("2024-12-21 14:30:00")
        val result = convertToTimestamp(date, time)

        assertEquals(expectedTimestamp, result)
    }

    @Test
    fun `convertToTimestamp throws exception for invalid date format`() {
        val invalidDate = "21-12-2024"
        val time = "14:30"

        assertThrows(DateTimeParseException::class.java) {
            convertToTimestamp(invalidDate, time)
        }
    }

    @Test
    fun `convertToTimestamp throws exception for invalid time format`() {
        val date = "2024-12-21"
        val invalidTime = "1430"

        assertThrows(DateTimeParseException::class.java) {
            convertToTimestamp(date, invalidTime)
        }
    }

    @Test
    fun `convertToTimestamp throws exception for empty date or time`() {
        val date = ""
        val time = "14:30"

        assertThrows(DateTimeParseException::class.java) {
            convertToTimestamp(date, time)
        }

        val validDate = "2024-12-21"
        val emptyTime = ""

        assertThrows(DateTimeParseException::class.java) {
            convertToTimestamp(validDate, emptyTime)
        }
    }

    @Test
    fun `convertToTimestamp throws exception for invalid combined input`() {
        val date = "2024-12-21"
        val time = "invalid-time"

        assertThrows(DateTimeParseException::class.java) {
            convertToTimestamp(date, time)
        }
    }
}