package cz.yav.scratchcards.presentation.scratch

import cz.yav.scratchcards.presentation.common.component.state.ScratchcardUiState

sealed interface ScratchScreenUiState {
    object Loading : ScratchScreenUiState

    data class Success(
        val cardState: ScratchcardUiState,
    ) : ScratchScreenUiState
}