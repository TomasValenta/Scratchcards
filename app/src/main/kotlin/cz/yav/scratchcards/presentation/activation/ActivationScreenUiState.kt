package cz.yav.scratchcards.presentation.activation

import cz.yav.scratchcards.presentation.common.component.state.ScratchcardUiState

sealed interface ActivationScreenUiState {
    object Loading : ActivationScreenUiState

    data class Success(
        val cardState: ScratchcardUiState,
        val postNotificationsPermissionVisible: Boolean,
    ) : ActivationScreenUiState
}