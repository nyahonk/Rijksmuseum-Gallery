@file:OptIn(ExperimentalMaterial3Api::class)

package com.nyahonk.rijksmuseumgallery.ui.screens.main

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.paging.LoadState
import androidx.paging.PagingData
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.itemsIndexed
import coil.compose.SubcomposeAsyncImage
import com.nyahonk.rijksmuseumgallery.R
import com.nyahonk.rijksmuseumgallery.models.ArtCollectionListItem
import com.nyahonk.rijksmuseumgallery.ui.screens.Screens
import kotlinx.coroutines.flow.Flow

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreenBody(
    navController: NavController, viewModel: MainScreenViewModel
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
            PopulateColumn(flow = viewModel.flow, innerPadding = innerPadding, navController)
        },
    )
}

@Composable
fun PopulateColumn(
    flow: Flow<PagingData<ArtCollectionListItem>>,
    innerPadding: PaddingValues,
    navController: NavController
) {

    val lazyPagingItems = flow.collectAsLazyPagingItems()

    LazyColumn(
        modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally
    ) {
        item {
            Spacer(Modifier.padding(innerPadding))
        }
        itemsIndexed(items = lazyPagingItems, key = { _, item -> item.id }) { index, item ->
            item?.let { artObject ->

                val needHeader =
                    (index == 0 || lazyPagingItems.peek(index - 1)?.author != item.author)
                ArtCollectionCard(artObject, needHeader) {
                    navController.navigate(
                        Screens.DetailsScreen.route + "?collectionName=${artObject.title}&collectionObjectNumber=${artObject.id}"
                    )
                }
            }
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
                                ?: stringResource(R.string.network_error_message)
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
    item: ArtCollectionListItem, withHeader: Boolean = false, onClick: () -> Unit
) {
    if (withHeader) Column {
        ArtCollectionCardHeader(title = item.title)
        ArtCollectionCardNoHeader(item = item, onClick)
    } else ArtCollectionCardNoHeader(item = item, onClick)
}

@Composable
fun ArtCollectionCardNoHeader(
    item: ArtCollectionListItem, onClick: () -> Unit
) {
    ElevatedCard(
        onClick = onClick,
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth(0.9f),
    ) {
        Column {
            SubcomposeAsyncImage(
                model = item.imageHeaderUrl, loading = {
                    CircularProgressIndicator()
                }, contentDescription = item.title
            )
            Text(text = item.title)
        }
    }
}

@Composable
fun ArtCollectionCardHeader(title: String) {
    ElevatedCard(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth(0.9f)
    ) {
        Text(
            modifier = Modifier
                .background(MaterialTheme.colorScheme.primary)
                .padding(4.dp)
                .fillMaxSize(), text = title, style = MaterialTheme.typography.headlineSmall
        )
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
            .padding(8.dp)
            .wrapContentWidth(Alignment.CenterHorizontally)
    )
}

@Composable
fun ErrorItem(
    message: String, modifier: Modifier = Modifier, onClickRetry: () -> Unit
) {
    Row(
        modifier = modifier.padding(8.dp),
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
            Text(text = stringResource(R.string.btn_retry))
        }
    }
}