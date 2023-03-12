package cz.yav.scratchcards.data.repository.model

sealed interface ActivationResult {
    object Failure : ActivationResult
    data class Success(val value: Long) : ActivationResult
}