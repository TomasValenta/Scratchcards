package cz.yav.scratchcards.presentation.common.component

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.ProvidableCompositionLocal
import androidx.compose.runtime.compositionLocalOf
import androidx.navigation.NavHostController
import cz.yav.scratchcards.domain.manager.navigation.NavigationManager
import cz.yav.scratchcards.domain.manager.navigation.model.Command

@Composable
fun Navigator(
    navigationManager: NavigationManager,
    navController: NavHostController = LocalNavController.current,
) {
    fun handleDestinationCommand(command: Command.Destination) {
        navController.navigate(command.route) {
            launchSingleTop = true
        }
    }

    LaunchedEffect(navigationManager.command) {
        navigationManager
            .command
            .collect { command ->
                when (command) {
                    is Command.Destination -> handleDestinationCommand(command)
                }
            }
    }
}

val LocalNavController: ProvidableCompositionLocal<NavHostController> = compositionLocalOf {
    error("[LocalNavController] NavHostController not provided!")
}