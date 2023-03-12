package cz.yav.scratchcards.presentation.scratch

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle

@Composable
fun ScratchScreenContainer(
    viewModel: ScratchScreenViewModel = hiltViewModel(),
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    ScratchScreen(
        state = state,
        onScratchClick = viewModel::onScratchClick,
    )
}
