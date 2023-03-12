package cz.yav.scratchcards.domain.manager.navigation

import cz.yav.scratchcards.domain.manager.navigation.model.Command
import kotlinx.coroutines.flow.SharedFlow

interface NavigationManager {
    val command: SharedFlow<Command>
    suspend fun navigate(command: Command)
}
