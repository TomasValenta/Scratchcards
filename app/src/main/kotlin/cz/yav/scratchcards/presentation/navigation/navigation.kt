package cz.yav.scratchcards.presentation.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.navArgument
import cz.yav.scratchcards.presentation.activation.ActivationScreenContainer
import cz.yav.scratchcards.presentation.overview.OverviewScreenContainer
import cz.yav.scratchcards.presentation.scratch.ScratchScreenContainer

sealed class ScratchcardRoute(
    val route: String,
    val routeRoot: String = route,
    val argumentName: String = "",
) {
    object Root : ScratchcardRoute("scratchcards")
    object Overview : ScratchcardRoute("overview")
    object Scratch : ScratchcardRoute("scratch/{cardUUID}", "scratch", "cardUUID")
    object Activation : ScratchcardRoute("activation/{cardUUID}", "activation", "cardUUID")
}

fun NavGraphBuilder.scratchcardGraph() {
    navigation(
        startDestination = ScratchcardRoute.Overview.route,
        route = ScratchcardRoute.Root.route,
    ) {
        composable(route = ScratchcardRoute.Overview.route) {
            OverviewScreenContainer()
        }
        composable(
            route = ScratchcardRoute.Scratch.route,
            arguments = listOf(navArgument(ScratchcardRoute.Scratch.argumentName) { type = NavType.StringType })
        ) {
            ScratchScreenContainer()
        }
        composable(
            route = ScratchcardRoute.Activation.route,
            arguments = listOf(navArgument(ScratchcardRoute.Activation.argumentName) { type = NavType.StringType })
        ) {
            ActivationScreenContainer()
        }
    }
}
