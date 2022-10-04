package com.nyahonk.rijksmuseumgallery.ui.screens.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.nyahonk.rijksmuseumgallery.usecase.ArtCollectionsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainScreenViewModel @Inject constructor(
    artCollectionsUseCase: ArtCollectionsUseCase
) : ViewModel() {

    val flow = Pager(config = PagingConfig(pageSize = 20)) {
        CollectionsPagingSource(artCollectionsUseCase)
    }.flow.cachedIn(viewModelScope)

}