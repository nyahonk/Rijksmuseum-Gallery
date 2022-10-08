@file:OptIn(ExperimentalCoroutinesApi::class)

package com.nyahonk.rijksmuseumgallery

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.navigation.compose.rememberNavController
import com.nyahonk.rijksmuseumgallery.ui.screens.main.MainScreenBody
import com.nyahonk.rijksmuseumgallery.ui.screens.main.MainScreenViewModel
import com.nyahonk.rijksmuseumgallery.ui.theme.RijksmuseumGalleryTheme
import com.nyahonk.rijksmuseumgallery.usecase.ArtCollectionsUseCase
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import javax.inject.Inject

@HiltAndroidTest
class MainScreenInstrumentedTest {

    @get:Rule(order = 1)
    var hiltRule = HiltAndroidRule(this)

    @get:Rule(order = 2)
    val composeTestRule = createComposeRule()

    @Inject
    lateinit var artCollectionsUseCase: ArtCollectionsUseCase
    lateinit var mockViewModel: MainScreenViewModel

    @Before
    fun setup() = runTest {
        hiltRule.inject()
        mockViewModel = MockMainScreenViewModel(artCollectionsUseCase)

        composeTestRule.setContent {
            RijksmuseumGalleryTheme {
                MainScreenBody(rememberNavController(), mockViewModel)
            }
        }
    }

    @Test
    fun checkLoadingIndicator() = runTest {
        (mockViewModel as MockMainScreenViewModel).mockPageWithState(MockMainScreenViewModel.State.StateLoading)

        composeTestRule.onNodeWithTag("LoadingView").assertExists()
    }

    @Test
    fun checkErrorItem() = runTest {

        (mockViewModel as MockMainScreenViewModel).mockPageWithState(MockMainScreenViewModel.State.StateError)

        composeTestRule.onNodeWithTag("ErrorItem").assertExists()
    }

    @Test
    fun checkAppendItem() = runTest {

        (mockViewModel as MockMainScreenViewModel).mockPageWithState(MockMainScreenViewModel.State.StateRefresh)

        composeTestRule.onNodeWithTag("LoadingItem").assertExists()
    }
}