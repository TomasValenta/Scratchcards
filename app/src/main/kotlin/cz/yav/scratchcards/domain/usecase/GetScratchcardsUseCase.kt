package cz.yav.scratchcards.domain.usecase

import cz.yav.scratchcards.data.repository.ScratchcardsRepository
import cz.yav.scratchcards.data.repository.model.Scratchcard
import kotlinx.coroutines.flow.Flow

class GetScratchcardsUseCase(
    private val scratchcardsRepository: ScratchcardsRepository,
) {
    operator fun invoke(): Flow<Set<Scratchcard>> = scratchcardsRepository.scratchcards
}
