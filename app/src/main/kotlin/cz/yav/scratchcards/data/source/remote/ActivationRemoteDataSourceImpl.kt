package cz.yav.scratchcards.data.source.remote

import cz.yav.scratchcards.BuildConfig
import cz.yav.scratchcards.data.source.remote.model.ActivateDto
import cz.yav.scratchcards.di.Unauthorized
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.http.*
import javax.inject.Inject

private const val PATH = "version"
private const val PARAM_CODE = "code"

class ActivationRemoteDataSourceImpl @Inject constructor(
    @Unauthorized private val client: HttpClient,
) : ActivationRemoteDataSource {

    override suspend fun activate(
        code: String,
    ): ActivateDto = client.get {
        url {
            protocol = URLProtocol.HTTPS
            host = BuildConfig.API_HOST
            path(PATH)
            parameters.append(PARAM_CODE, code)
        }
        contentType(ContentType.Application.Json)
    }.body()
}