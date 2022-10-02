package com.nyahonk.rijksmuseumgallery.ui.screens

sealed class Screens(val route: String)  {
    object MainScreen : Screens("main")
    object DetailsScreen : Screens("details")
}