package cz.yav.scratchcards.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import cz.yav.scratchcards.presentation.common.component.SystemBarsColorSetter
import dagger.hilt.android.AndroidEntryPoint
import cz.yav.scratchcards.presentation.navigation.ScratchcardRoute
import cz.yav.scratchcards.domain.manager.navigation.NavigationManager
import cz.yav.scratchcards.presentation.common.component.LocalNavController
import cz.yav.scratchcards.presentation.common.component.Navigator
import cz.yav.scratchcards.presentation.navigation.scratchcardGraph
import cz.yav.scratchcards.presentation.theme.ScratchcardAppTheme
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var navigationManager: NavigationManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val navController = rememberNavController()

            ScratchcardAppTheme {
                SystemBarsColorSetter()
                CompositionLocalProvider(
                    LocalNavController provides navController,
                ) {
                    Navigator(navigationManager = navigationManager)
                    Surface(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(16.dp),
                        color = MaterialTheme.colorScheme.background
                    ) {
                        NavHost(
                            navController = navController,
                            startDestination = ScratchcardRoute.Root.route,
                        ) {
                            scratchcardGraph()
                        }
                    }
                }
            }
        }
    }
}
