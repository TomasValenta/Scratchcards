package cz.yav.scratchcards.data.source.remote.client

import io.ktor.client.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.json.Json

val unauthorizedHttpClient: HttpClient by lazy {
    HttpClient {
        installJsonPlugin()
        expectSuccess = true
    }
}

private fun HttpClientConfig<*>.installJsonPlugin() {
    install(ContentNegotiation) {
        json(
            json = Json {
                ignoreUnknownKeys = true
            },
            contentType = ContentType.Text.Html
        )
    }
}
