package cz.yav.scratchcards.data.source.remote

import cz.yav.scratchcards.data.source.remote.model.ActivateDto

interface ActivationRemoteDataSource {
    suspend fun activate(code: String): ActivateDto
}