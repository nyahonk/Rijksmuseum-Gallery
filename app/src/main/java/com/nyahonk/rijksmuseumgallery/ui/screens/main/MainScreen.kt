@file:OptIn(ExperimentalMaterial3Api::class)

package com.nyahonk.rijksmuseumgallery.ui.screens.main

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.paging.LoadState
import androidx.paging.PagingData
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import com.nyahonk.rijksmuseumgallery.models.ArtCollectionListItem
import kotlinx.coroutines.flow.Flow

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreenBody(
    navController: NavController,
    viewModel: MainScreenViewModel
) {

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
            PopulateColumn(flow = viewModel.flow, innerPadding = innerPadding)
        },
    )
}

@Composable
fun PopulateColumn(flow: Flow<PagingData<ArtCollectionListItem>>, innerPadding: PaddingValues) {

    val lazyPagingItems = flow.collectAsLazyPagingItems()

    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        items(
            items = lazyPagingItems,
            key = { it.id }
        ) { item ->
            item?.let { artObject ->
                ArtCollectionCard(artObject, innerPadding) {
//                    navController.navigate(Screens.DetailsScreen.route)
                }
            } ?: ArtCollectionCardPlaceHolder(innerPadding) {}
        }
        lazyPagingItems.apply {
            when {
                loadState.refresh is LoadState.Loading -> {
                    item { LoadingView(modifier = Modifier.fillParentMaxSize()) }
                }
                loadState.append is LoadState.Loading -> {
                    item { LoadingItem() }
                }
                loadState.append is LoadState.Error -> {
                    item {
                        ErrorItem(
                            message = (loadState.append as LoadState.Error).error.message
                                ?: "Network Error"
                        ) {
                            retry()
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun ArtCollectionCard(
    item: ArtCollectionListItem,
    innerPadding: PaddingValues,
    onClick: () -> Unit
) {
    ElevatedCard(
        onClick = onClick,
        modifier = Modifier
            .padding(innerPadding)
            .fillMaxWidth(0.9f),
    ) {
        Column {
            Text(text = item.title)
            Text(text = item.author)
            Text(text = item.imageHeaderUrl)
        }
    }
}

@Composable
fun ArtCollectionCardPlaceHolder(innerPadding: PaddingValues, onClick: () -> Unit) {
    ElevatedCard(
        onClick = onClick,
        modifier = Modifier
            .padding(innerPadding)
            .fillMaxWidth(0.9f),
    ) {
        Text(text = "title")
        Text(text = "author")
        Text(text = "imageHeaderUrl")
    }
}

@Composable
fun LoadingView(
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        CircularProgressIndicator()
    }
}

@Composable
fun LoadingItem() {
    CircularProgressIndicator(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .wrapContentWidth(Alignment.CenterHorizontally)
    )
}

@Composable
fun ErrorItem(
    message: String,
    modifier: Modifier = Modifier,
    onClickRetry: () -> Unit
) {
    Row(
        modifier = modifier.padding(16.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = message,
            maxLines = 1,
            modifier = Modifier.weight(1f),
            style = MaterialTheme.typography.bodyMedium,
            color = Color.Red
        )
        OutlinedButton(onClick = onClickRetry) {
            Text(text = "Try again")
        }
    }
}