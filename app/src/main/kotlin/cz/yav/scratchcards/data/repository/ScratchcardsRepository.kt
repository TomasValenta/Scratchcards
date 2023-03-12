package cz.yav.scratchcards.data.repository

import cz.yav.scratchcards.data.repository.model.ActivationResult
import cz.yav.scratchcards.data.repository.model.Scratchcard
import kotlinx.coroutines.flow.Flow

interface ScratchcardsRepository {
    val scratchcards: Flow<Set<Scratchcard>>
    suspend fun updateScratchcard(scratchcard: Scratchcard)
    suspend fun activateScratchcardCode(code: String): ActivationResult
}