package cz.yav.scratchcards.data.repository

import cz.yav.scratchcards.data.source.remote.ActivationRemoteDataSource
import cz.yav.scratchcards.data.source.remote.model.ActivateDto
import cz.yav.scratchcards.data.repository.model.ActivationResult
import cz.yav.scratchcards.data.repository.model.Scratchcard
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class ScratchcardsRepositoryImpl @Inject constructor(
    private val activationRemoteDataSource: ActivationRemoteDataSource,
) : ScratchcardsRepository {

    private val _scratchcards = MutableStateFlow(emptySet<Scratchcard>())
    override val scratchcards: Flow<Set<Scratchcard>> = _scratchcards

    override suspend fun updateScratchcard(scratchcard: Scratchcard) {
        _scratchcards.value = _scratchcards.value
            .toMutableSet()
            .map { if (it.uuid == scratchcard.uuid) scratchcard else it }
            .toSet()
    }

    override suspend fun activateScratchcardCode(code: String) = try {
        activationRemoteDataSource.activate(code).toActivationResult()
    } catch (e: Exception) {
        ActivationResult.Failure
    }

    init {
        CoroutineScope(Dispatchers.IO)
            .launch {
                delay(1000) // Network call simulation
                _scratchcards.value = (1..5)
                    .map { Scratchcard(value = it * 10) }
                    .toSet()
            }
    }
}

private fun ActivateDto.toActivationResult() = ActivationResult.Success(value = android)