package cz.yav.scratchcards.data.repository.model

import java.util.UUID

data class Scratchcard(
    val value: Int,
    val uuid: String = UUID.randomUUID().toString(),
    val state: State = State.New,
) {
    sealed class State(
        open val code: String? = null,
    ) {
        object New : State()
        object ScratchInProgress : State()
        data class Scratched(override val code: String) : State()
        data class ActivationInProgress(override val code: String) : State()
        data class Activated(override val code: String) : State()
        data class Rejected(override val code: String, val notified: Boolean = false) : State()
    }
}