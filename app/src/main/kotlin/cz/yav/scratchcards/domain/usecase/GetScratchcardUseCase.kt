package cz.yav.scratchcards.domain.usecase

import cz.yav.scratchcards.data.repository.ScratchcardsRepository
import cz.yav.scratchcards.data.repository.model.Scratchcard
import kotlinx.coroutines.flow.firstOrNull

class GetScratchcardUseCase(
    private val scratchcardsRepository: ScratchcardsRepository,
) {
    suspend operator fun invoke(
        uuid: String,
    ): Scratchcard? = scratchcardsRepository.scratchcards.firstOrNull()?.firstOrNull { it.uuid == uuid }
}
