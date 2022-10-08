package com.nyahonk.rijksmuseumgallery

import androidx.compose.ui.test.assertHasClickAction
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import com.nyahonk.rijksmuseumgallery.ui.MainActivity
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@HiltAndroidTest
class NavigationInstrumentedTest {

    @get:Rule(order = 1)
    var hiltRule = HiltAndroidRule(this)

    @get:Rule(order = 2)
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    @Before
    fun doBefore() {
        hiltRule.inject()
    }

    @Test
    fun checkTitleIsCorrect() {
        composeTestRule.onNodeWithText("Rijksmuseum Gallery").assertExists()
    }

    @Test
    fun checkNavigationToDetailsScreen() {
        composeTestRule.onNodeWithText("title1").assertExists()
        composeTestRule.onNodeWithText("title1").assertHasClickAction()
        composeTestRule.onNodeWithText("title1").performClick()

        composeTestRule.onNodeWithTag("DetailsScreenBody").assertExists()
        composeTestRule.onNodeWithText("title1").assertExists()
    }
}