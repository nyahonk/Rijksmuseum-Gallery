package com.nyahonk.rijksmuseumgallery

import androidx.paging.LoadState
import androidx.paging.LoadStates
import androidx.paging.PagingData
import com.nyahonk.rijksmuseumgallery.models.ArtCollectionListItem
import com.nyahonk.rijksmuseumgallery.ui.screens.main.MainScreenViewModel
import com.nyahonk.rijksmuseumgallery.usecase.ArtCollectionsUseCase
import kotlinx.coroutines.flow.MutableSharedFlow

class MockMainScreenViewModel(
    private val useCase: ArtCollectionsUseCase
) : MainScreenViewModel(useCase) {

    private val flow = MutableSharedFlow<PagingData<ArtCollectionListItem>>()

    override fun getPagingFlow() = flow

    suspend fun mockPageWithState(state: State) {
        val loadState = when (state) {
            State.StateLoading -> LoadState.Loading
            State.StateError -> LoadState.Error(Throwable("test error"))
            State.StateRefresh -> LoadState.Loading
        }

        if (state is State.StateLoading) {
            flow.emit(
                PagingData.empty(
                    LoadStates(
                        refresh = loadState,
                        prepend = LoadState.NotLoading(true),
                        append = LoadState.NotLoading(true)
                    )
                )
            )
        } else {
            flow.emit(
                PagingData.empty(
                    LoadStates(
                        refresh = LoadState.NotLoading(true),
                        prepend = LoadState.NotLoading(true),
                        append = loadState
                    )
                )
            )
        }

    }

    sealed class State {
        object StateLoading : State()
        object StateRefresh : State()
        object StateError : State()
    }
}