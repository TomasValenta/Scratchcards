package cz.yav.scratchcards.presentation.common.component

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import com.google.accompanist.systemuicontroller.SystemUiController
import com.google.accompanist.systemuicontroller.rememberSystemUiController

@Composable
fun SystemBarsColorSetter(
    color: Color = MaterialTheme.colorScheme.background,
    systemUiController: SystemUiController = rememberSystemUiController(),
) {
    SideEffect {
        systemUiController.setSystemBarsColor(color)
    }
}
