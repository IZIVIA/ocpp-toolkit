package com.izivia.ocpp.core20.model.reservenow

import com.fasterxml.jackson.databind.annotation.JsonDeserialize
import com.fasterxml.jackson.databind.annotation.JsonSerialize
import com.izivia.ocpp.core20.model.common.IdTokenType
import com.izivia.ocpp.core20.model.reservenow.enumeration.ConnectorEnumType
import com.izivia.ocpp.utils.InstantDeserializer
import com.izivia.ocpp.utils.InstantSerializer
import kotlinx.datetime.Instant

data class ReserveNowReq(
    val id: Int,
    @JsonSerialize(using = InstantSerializer::class)
    @JsonDeserialize(using = InstantDeserializer::class)
    val expiryDateTime: Instant,
    val connectorType: ConnectorEnumType? = null,
    val evseId: Int? = null,
    val idToken: IdTokenType,
    val groupIdToken: IdTokenType? =null
)