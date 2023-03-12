package cz.yav.scratchcards.presentation.common.mapper

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import cz.yav.scratchcards.data.repository.model.Scratchcard
import cz.yav.scratchcards.presentation.common.component.state.ScratchcardUiState

fun Scratchcard.toScratchcardUiState(
    scratchButtonVisible: Boolean = true,
    activateButtonVisible: Boolean = true,
) = ScratchcardUiState(
    value = value,
    code = state.code,
    imageColor = when (state) {
        Scratchcard.State.New, Scratchcard.State.ScratchInProgress -> Color.Gray
        else -> Color.LightGray
    },
    imageBorderColor = when (state) {
        is Scratchcard.State.Activated -> Color.Green
        is Scratchcard.State.Rejected -> Color.Red
        else -> Color.Gray
    },
    imageBorder = when (state) {
        Scratchcard.State.New -> 0.dp
        else -> 3.dp
    },
    progressVisible = state is Scratchcard.State.ScratchInProgress || state is Scratchcard.State.ActivationInProgress,
    scratchButtonVisible = scratchButtonVisible,
    scratchButtonEnabled = state is Scratchcard.State.New,
    activateButtonVisible = activateButtonVisible,
    activateButtonEnabled = state is Scratchcard.State.Scratched,
)