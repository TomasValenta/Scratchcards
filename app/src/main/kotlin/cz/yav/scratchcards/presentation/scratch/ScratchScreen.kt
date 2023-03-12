package cz.yav.scratchcards.presentation.scratch

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import cz.yav.scratchcards.presentation.common.component.Loading
import cz.yav.scratchcards.presentation.common.component.ScratchcardItem

@Composable
fun ScratchScreen(
    state: ScratchScreenUiState,
    onScratchClick: () -> Unit,
) {

    when (state) {
        ScratchScreenUiState.Loading -> {
            Loading()
        }
        is ScratchScreenUiState.Success -> {
            Box(
                modifier = Modifier
                    .fillMaxSize(),
                contentAlignment = Alignment.Center,
            ) {
                ScratchcardItem(
                    state = state.cardState,
                    onScratchClick = onScratchClick,
                )
            }
        }
    }
}
