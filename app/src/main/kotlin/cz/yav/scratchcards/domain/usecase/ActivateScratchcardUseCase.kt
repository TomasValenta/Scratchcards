package cz.yav.scratchcards.domain.usecase

import cz.yav.scratchcards.data.repository.ScratchcardsRepository
import cz.yav.scratchcards.data.repository.model.ActivationResult
import cz.yav.scratchcards.data.repository.model.Scratchcard

class ActivateScratchcardUseCase(
    private val scratchcardsRepository: ScratchcardsRepository,
    private val updateScratchcardUseCase: UpdateScratchcardUseCase,
    private val activationSuccessThreshold: Long = 80_000,
) {
    suspend operator fun invoke(
        scratchcard: Scratchcard,
    ) {
        if (scratchcard.state !is Scratchcard.State.Scratched) {
            return
        }

        updateScratchcardUseCase(scratchcard.copy(state = Scratchcard.State.ActivationInProgress(scratchcard.state.code)))

        val result = scratchcardsRepository.activateScratchcardCode(scratchcard.state.code)
        updateScratchcardUseCase(
            scratchcard.copy(
                state = when (result) {
                    is ActivationResult.Success -> {
                        if (result.value > activationSuccessThreshold) {
                            Scratchcard.State.Activated(scratchcard.state.code)
                        } else {
                            Scratchcard.State.Rejected(scratchcard.state.code)
                        }
                    }
                    ActivationResult.Failure -> scratchcard.state
                }
            )
        )
    }
}
