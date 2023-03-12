package cz.yav.scratchcards.presentation.common.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.ColorPainter
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

import cz.yav.scratchcards.R
import cz.yav.scratchcards.presentation.common.component.state.ScratchcardUiState

@Composable
fun ScratchcardItem(
    state: ScratchcardUiState,
    modifier: Modifier = Modifier,
    onScratchClick: () -> Unit = {},
    onActivateClick: () -> Unit = {},
) {
    ElevatedCard(
        modifier = modifier
            .fillMaxWidth(),
    ) {
        Column {
            Box(
                contentAlignment = Alignment.Center,
            ) {
                Image(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(148.dp)
                        .clip(MaterialTheme.shapes.medium)
                        .border(state.imageBorder, state.imageBorderColor, MaterialTheme.shapes.medium),
                    painter = ColorPainter(state.imageColor),
                    contentDescription = null,
                )
                if (state.progressVisible) {
                    CircularProgressIndicator()
                }
                state.code?.let {
                    Text(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp),
                        text = it,
                        textAlign = TextAlign.Center,
                        style = MaterialTheme.typography.titleMedium
                    )
                }
            }
            Text(
                modifier = Modifier
                    .padding(8.dp),
                text = stringResource(id = R.string.scratchcard_value, state.value),
                style = MaterialTheme.typography.headlineMedium
            )
            Row(
                modifier = Modifier
                    .padding(start = 8.dp, bottom = 8.dp, end = 8.dp),
            ) {
                if (state.scratchButtonVisible) {
                    Button(
                        modifier = Modifier
                            .padding(end = 8.dp),
                        onClick = onScratchClick,
                        enabled = state.scratchButtonEnabled,
                    ) {
                        Text(text = stringResource(id = R.string.scratch))
                    }
                }
                if (state.activateButtonVisible) {
                    Button(
                        modifier = Modifier
                            .padding(end = 8.dp),
                        onClick = onActivateClick,
                        enabled = state.activateButtonEnabled,
                    ) {
                        Text(text = stringResource(id = R.string.activate))
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun ScratchcardItemDefaultPreview() {
    ScratchcardItem(
        state = ScratchcardUiState(
            value = 5
        ),
    )
}

@Preview
@Composable
fun ScratchcardItemProgressPreview() {
    ScratchcardItem(
        state = ScratchcardUiState(
            value = 5,
            progressVisible = true,
        ),
    )
}

@Preview
@Composable
fun ScratchcardItemCodePreview() {
    ScratchcardItem(
        state = ScratchcardUiState(
            value = 5,
            code = "d984163b-1afb-4a3f-9073-5568b556fff6",
            imageColor = Color.LightGray,
            imageBorder = 5.dp,
            imageBorderColor = Color.Gray,
        ),
    )
}
