@file:OptIn(ExperimentalMaterial3Api::class)

package com.nyahonk.rijksmuseumgallery.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavController

@Composable
fun MainScreenBody(navController: NavController) {

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Rijksmuseum Gallery") },
                colors = TopAppBarDefaults.smallTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                )
            )
        },
        content = { innerPadding ->
            Column(
                modifier = Modifier.fillMaxWidth(1f),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                ElevatedCard(
                    onClick = { navController.navigate(Screens.DetailsScreen.route) },
                    modifier = Modifier
                        .padding(innerPadding)
                        .fillMaxWidth(0.9f),
                ) {
                    Text(text = "Text")
                }
            }
        },
    )
}