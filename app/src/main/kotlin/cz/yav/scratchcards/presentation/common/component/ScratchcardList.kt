package cz.yav.scratchcards.presentation.common.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import cz.yav.scratchcards.presentation.overview.OverviewScreenUiState

@Composable
fun ScratchcardList(
    items: List<OverviewScreenUiState.CardUiState>,
    onScratchClick: (OverviewScreenUiState.CardUiState) -> Unit,
    onActivateClick: (OverviewScreenUiState.CardUiState) -> Unit,
    modifier: Modifier = Modifier,
) {
    LazyColumn(
        modifier = modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        items(
            items = items,
            key = OverviewScreenUiState.CardUiState::uuid,
        ) { item ->
            ScratchcardItem(
                state = item.state,
                onScratchClick = { onScratchClick(item) },
                onActivateClick = { onActivateClick(item) },
            )
        }
    }
}