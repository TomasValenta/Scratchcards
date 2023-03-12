package cz.yav.scratchcards.domain.manager.navigation.model

sealed interface Command {
    class Destination(
        val route: String,
    ) : Command
}