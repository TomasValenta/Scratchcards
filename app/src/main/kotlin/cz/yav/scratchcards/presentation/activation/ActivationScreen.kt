package cz.yav.scratchcards.presentation.activation

import android.Manifest
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.google.accompanist.permissions.rememberPermissionState
import com.google.accompanist.permissions.shouldShowRationale
import cz.yav.scratchcards.extension.isAtLeastTiramisu
import cz.yav.scratchcards.presentation.activation.component.PostNotificationsPermissionDialog
import cz.yav.scratchcards.presentation.common.component.Loading
import cz.yav.scratchcards.presentation.common.component.ScratchcardItem

@Composable
fun ActivationScreen(
    state: ActivationScreenUiState,
    onActivateClick: () -> Unit,
    onPostNotificationsConfirmButtonClick: () -> Unit,
    onPostNotificationsDismissButtonClick: () -> Unit,
) {

    val postNotificationPermissionState = rememberPermissionState(if (isAtLeastTiramisu) Manifest.permission.POST_NOTIFICATIONS else "")

    when (state) {
        ActivationScreenUiState.Loading -> {
            Loading()
        }
        is ActivationScreenUiState.Success -> {
            Box(
                modifier = Modifier
                    .fillMaxSize(),
                contentAlignment = Alignment.Center,
            ) {
                ScratchcardItem(
                    state = state.cardState,
                    onActivateClick = onActivateClick,
                )
                if (state.postNotificationsPermissionVisible) {
                    PostNotificationsPermissionDialog(
                        shouldShowRationale = postNotificationPermissionState.status.shouldShowRationale,
                        onConfirmButtonClick = {
                            onPostNotificationsConfirmButtonClick()
                            postNotificationPermissionState.launchPermissionRequest()
                        },
                        onDismissButtonClick = onPostNotificationsDismissButtonClick,
                    )
                }
            }
        }
    }
}
