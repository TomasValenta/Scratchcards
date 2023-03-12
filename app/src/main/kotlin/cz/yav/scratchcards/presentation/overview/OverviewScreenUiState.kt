package cz.yav.scratchcards.presentation.overview

import cz.yav.scratchcards.presentation.common.component.state.ScratchcardUiState

sealed interface OverviewScreenUiState {
    object Loading : OverviewScreenUiState

    data class Success(
        val cardsUiState: List<CardUiState> = emptyList(),
    ): OverviewScreenUiState

    data class CardUiState(
        val uuid: String,
        val state: ScratchcardUiState,
    )
}