package cz.yav.scratchcards.presentation.overview

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import cz.yav.scratchcards.presentation.common.component.Loading
import cz.yav.scratchcards.presentation.common.component.ScratchcardList
import cz.yav.scratchcards.presentation.common.component.state.ScratchcardUiState

@Composable
fun OverviewScreen(
    state: OverviewScreenUiState,
    onScratchClick: (OverviewScreenUiState.CardUiState) -> Unit,
    onActivateClick: (OverviewScreenUiState.CardUiState) -> Unit,
) {
    when (state) {
        OverviewScreenUiState.Loading -> {
            Loading()
        }
        is OverviewScreenUiState.Success -> {
            ScratchcardList(
                state.cardsUiState,
                onScratchClick = onScratchClick,
                onActivateClick = onActivateClick,
            )
        }
    }
}

@Preview
@Composable
fun OverviewScreenPreview() {
    OverviewScreen(
        state = OverviewScreenUiState.Success(
            cardsUiState = listOf(
                OverviewScreenUiState.CardUiState(
                    uuid = "1",
                    state = ScratchcardUiState(value = 5)
                ),
                OverviewScreenUiState.CardUiState(
                    uuid = "2",
                    state = ScratchcardUiState(value = 10)
                ),
                OverviewScreenUiState.CardUiState(
                    uuid = "3",
                    state = ScratchcardUiState(value = 15)
                ),
            )
        ),
        onScratchClick = {},
        onActivateClick = {},
    )
}