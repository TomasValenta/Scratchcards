package cz.yav.scratchcards.data.source.remote.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ActivateDto(
    @SerialName("android") val android: Long,
)
