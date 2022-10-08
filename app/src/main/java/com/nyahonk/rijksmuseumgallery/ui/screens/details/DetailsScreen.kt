@file:OptIn(ExperimentalMaterial3Api::class)

package com.nyahonk.rijksmuseumgallery.ui.screens.details

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import coil.compose.SubcomposeAsyncImage
import com.nyahonk.rijksmuseumgallery.R

@Composable
fun DetailsScreenBody(
    viewModel: DetailsScreenViewModel
) {

    Scaffold(
        modifier = Modifier.testTag("DetailsScreenBody"),
        topBar = {
            TopAppBar(
                title = { Text(viewModel.collectionName) },
                colors = TopAppBarDefaults.smallTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                )
            )
        },
        content = { innerPadding ->
            val artObject = viewModel.liveData.observeAsState()

            Column(modifier = Modifier.padding(innerPadding)) {
                SubcomposeAsyncImage(
                    model = artObject.value?.url,
                    loading = {
                        CircularProgressIndicator()
                    },
                    contentDescription = viewModel.collectionName)
                Text(text = stringResource(R.string.details_screen_author, artObject.value?.author ?: ""))
                Text(text = artObject.value?.description ?: "")
            }
        },
    )
}