package cz.yav.scratchcards.mock

import cz.yav.scratchcards.data.repository.ScratchcardsRepository
import cz.yav.scratchcards.data.repository.model.ActivationResult
import cz.yav.scratchcards.data.repository.model.Scratchcard
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow

class ScratchcardsRepositoryMockImpl(
    initialScratchcards: Set<Scratchcard> = emptySet(),
    var activationResult: ActivationResult = ActivationResult.Failure,
) : ScratchcardsRepository {

    private val _scratchcards = MutableStateFlow(initialScratchcards)
    override val scratchcards: Flow<Set<Scratchcard>> = _scratchcards

    override suspend fun updateScratchcard(scratchcard: Scratchcard) {
        _scratchcards.value = _scratchcards.value
            .toMutableSet()
            .map { if (it.uuid == scratchcard.uuid) scratchcard else it }
            .toSet()
    }

    override suspend fun activateScratchcardCode(code: String) = activationResult
}