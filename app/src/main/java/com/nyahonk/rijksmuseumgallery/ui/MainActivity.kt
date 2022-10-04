package com.nyahonk.rijksmuseumgallery.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.nyahonk.rijksmuseumgallery.ui.screens.Screens
import com.nyahonk.rijksmuseumgallery.ui.screens.details.DetailsScreenBody
import com.nyahonk.rijksmuseumgallery.ui.screens.details.DetailsScreenViewModel
import com.nyahonk.rijksmuseumgallery.ui.screens.main.MainScreenBody
import com.nyahonk.rijksmuseumgallery.ui.theme.RijksmuseumGalleryTheme
import com.nyahonk.rijksmuseumgallery.utils.addFirstNavArgument
import com.nyahonk.rijksmuseumgallery.utils.addNextNavArgument
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RunApp()
        }
    }
}

@Composable
fun RunApp() {
    RijksmuseumGalleryTheme {
        // A surface container using the 'background' color from the theme
        val navController = rememberNavController()
        val backstackEntry = navController.currentBackStackEntryAsState()
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            NavHost(navController)
        }
    }
}

@Composable
fun NavHost(navController: NavHostController, modifier: Modifier = Modifier) {
    androidx.navigation.compose.NavHost(
        navController = navController,
        startDestination = Screens.MainScreen.route,
        modifier = modifier
    ) {
        composable(Screens.MainScreen.route) {
            MainScreenBody(navController = navController, hiltViewModel())
        }
        composable(
            route = Screens.DetailsScreen.route
                .addFirstNavArgument(DetailsScreenViewModel.COLLECTION_NAME)
                .addNextNavArgument(DetailsScreenViewModel.COLLECTION_OBJECT_NUMBER),
            arguments = listOf(
                navArgument(DetailsScreenViewModel.COLLECTION_NAME) { defaultValue = "" },
                navArgument(DetailsScreenViewModel.COLLECTION_OBJECT_NUMBER) { defaultValue = "" })
        ) {
            DetailsScreenBody(hiltViewModel())
        }
    }
}