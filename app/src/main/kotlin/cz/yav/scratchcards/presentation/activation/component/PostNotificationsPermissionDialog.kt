package cz.yav.scratchcards.presentation.activation.component

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import cz.yav.scratchcards.R

@Composable
fun PostNotificationsPermissionDialog(
    shouldShowRationale: Boolean = false,
    onConfirmButtonClick: () -> Unit,
    onDismissButtonClick: () -> Unit,
) {
    AlertDialog(
        title = {
            Text(text = stringResource(id = R.string.get_notified))
        },
        text = {
            Text(
                text = stringResource(
                    id = if (shouldShowRationale) {
                        R.string.if_you_want_receive_activation_status_notifications_text
                    } else {
                        R.string.stay_on_top_of_your_scratchcards_activation_statuses
                    }
                )
            )
        },
        onDismissRequest = onDismissButtonClick,
        confirmButton = {
            TextButton(
                onClick = onConfirmButtonClick
            ) {
                Text(text = stringResource(id = R.string.im_in))
            }
        },
        dismissButton = {
            TextButton(
                onClick = onDismissButtonClick
            ) {
                Text(text = stringResource(id = R.string.skip))
            }
        }
    )
}