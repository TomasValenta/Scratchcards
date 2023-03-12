package cz.yav.scratchcards.presentation.common.component.state

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

data class ScratchcardUiState(
    val value: Int,
    val imageColor: Color = Color.Gray,
    val imageBorder: Dp = 0.dp,
    val imageBorderColor: Color = Color.Gray,
    val scratchButtonEnabled: Boolean = true,
    val scratchButtonVisible: Boolean = true,
    val activateButtonEnabled: Boolean = true,
    val activateButtonVisible: Boolean = true,
    val progressVisible: Boolean = false,
    val code: String? = null,
)
