package com.izivia.ocpp.core20.model.common

import com.fasterxml.jackson.databind.annotation.JsonDeserialize
import com.fasterxml.jackson.databind.annotation.JsonSerialize
import com.izivia.ocpp.core20.model.common.enumeration.AuthorizationStatusEnumType
import com.izivia.ocpp.utils.InstantDeserializer
import com.izivia.ocpp.utils.InstantSerializer
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
