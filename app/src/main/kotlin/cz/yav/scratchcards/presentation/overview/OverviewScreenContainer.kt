package cz.yav.scratchcards.presentation.overview

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle

@Composable
fun OverviewScreenContainer(
    viewModel: OverviewScreenViewModel = hiltViewModel(),
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    OverviewScreen(
        state = state,
        onScratchClick = viewModel::onScratchClick,
        onActivateClick = viewModel::onActivateClick,
    )
}
