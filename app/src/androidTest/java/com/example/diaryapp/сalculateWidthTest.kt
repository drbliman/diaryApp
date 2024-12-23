package com.example.diaryapp

import android.content.res.Configuration
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import com.example.diaryapp.utils.calculateWidth
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test

class CalculateWidthTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun calculateWidth_returnsCorrectValue_whenScreenWidthDpIs300() {
        composeTestRule.setContent {
            val result = calculateWidthForTest(300)
            assertEquals(200, result)
        }
    }

    @Test
    fun calculateWidth_returnsCorrectValue_whenScreenWidthDpIs500() {
        composeTestRule.setContent {
            val result = calculateWidthForTest(500)
            assertEquals(400, result)
        }
    }

    @Test
    fun calculateWidth_returnsCorrectValue_whenScreenWidthDpIs100() {
        composeTestRule.setContent {
            val result = calculateWidthForTest(100)
            assertEquals(0, result)
        }
    }
}

@Composable
fun calculateWidthForTest(screenWidthDp: Int): Int {
    var result = 0
    CompositionLocalProvider(
        LocalConfiguration provides createTestConfiguration(screenWidthDp)
    ) {
        result = calculateWidth()
    }
    return result
}


fun createTestConfiguration(screenWidthDp: Int): Configuration {
    return Configuration().apply {
        setToDefaults()
        this.screenWidthDp = screenWidthDp
    }
}