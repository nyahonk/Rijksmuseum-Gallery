package com.nyahonk.rijksmuseumgallery.ui.screens.details

import androidx.lifecycle.LiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.nyahonk.rijksmuseumgallery.models.ArtObjectDetailsItem
import com.nyahonk.rijksmuseumgallery.usecase.ArtCollectionsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DetailsScreenViewModel @Inject constructor(
    artCollectionsUseCase: ArtCollectionsUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    val collectionName: String = checkNotNull(savedStateHandle[COLLECTION_NAME])
    private val collectionObjectNumber: String = checkNotNull(savedStateHandle[COLLECTION_OBJECT_NUMBER])

    val liveData: LiveData<ArtObjectDetailsItem> = liveData {
        val data = artCollectionsUseCase.getCollectionDetails(collectionObjectNumber)
        emit(data)
    }

    companion object {

        const val COLLECTION_NAME = "collectionName"
        const val COLLECTION_OBJECT_NUMBER = "collectionObjectNumber"
    }

}