package fr.simatix.cs.simulator.core20.model.common

import com.fasterxml.jackson.databind.annotation.JsonDeserialize
import com.fasterxml.jackson.databind.annotation.JsonSerialize
import fr.simatix.cs.simulator.core20.model.common.enumeration.AuthorizationStatusEnumType
import fr.simatix.cs.simulator.utils.InstantDeserializer
import fr.simatix.cs.simulator.utils.InstantSerializer
import kotlinx.datetime.Instant

data class IdTokenInfoType(
    val status: AuthorizationStatusEnumType,

    @JsonSerialize(using = InstantSerializer::class)
    @JsonDeserialize(using = InstantDeserializer::class)
    val cacheExpiryDateTime: Instant? = null,

    val chargingPriority: Int? = null,
    val language1: String? = null,
    val evseId: List<Int>? = null,
    val language2: String? = null,
    val groupIdToken: IdTokenType? = null,
    val personalMessage: MessageContentType? = null
)
