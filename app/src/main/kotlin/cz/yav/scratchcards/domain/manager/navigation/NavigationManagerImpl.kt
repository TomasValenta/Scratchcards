package cz.yav.scratchcards.domain.manager.navigation

import cz.yav.scratchcards.domain.manager.navigation.model.Command
import kotlinx.coroutines.flow.MutableSharedFlow
import javax.inject.Inject

class NavigationManagerImpl @Inject constructor() : NavigationManager {

    override val command: MutableSharedFlow<Command> = MutableSharedFlow()

    override suspend fun navigate(command: Command) {
        this.command.emit(command)
    }
}
