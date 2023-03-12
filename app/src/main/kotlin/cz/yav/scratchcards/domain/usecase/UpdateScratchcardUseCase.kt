package cz.yav.scratchcards.domain.usecase

import cz.yav.scratchcards.data.repository.ScratchcardsRepository
import cz.yav.scratchcards.data.repository.model.Scratchcard

class UpdateScratchcardUseCase(
    private val scratchcardsRepository: ScratchcardsRepository,
) {
    suspend operator fun invoke(
        scratchcard: Scratchcard,
    ) = scratchcardsRepository.updateScratchcard(scratchcard)
}
