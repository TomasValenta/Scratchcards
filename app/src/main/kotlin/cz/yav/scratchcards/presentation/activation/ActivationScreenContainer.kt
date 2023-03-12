package cz.yav.scratchcards.presentation.activation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle

@Composable
fun ActivationScreenContainer(
    viewModel: ActivationScreenViewModel = hiltViewModel(),
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    ActivationScreen(
        state = state,
        onActivateClick = viewModel::onActivateClick,
        onPostNotificationsConfirmButtonClick = viewModel::onPostNotificationsConfirmButtonClick,
        onPostNotificationsDismissButtonClick = viewModel::onPostNotificationsDismissButtonClick,
    )
}
