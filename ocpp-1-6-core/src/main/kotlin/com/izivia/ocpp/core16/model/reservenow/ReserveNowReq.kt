package com.izivia.ocpp.core16.model.reservenow

import com.fasterxml.jackson.databind.annotation.JsonDeserialize
import com.fasterxml.jackson.databind.annotation.JsonSerialize
import com.izivia.ocpp.utils.InstantDeserializer
import com.izivia.ocpp.utils.InstantSerializer
import kotlinx.datetime.Instant

data class ReserveNowReq(
    val connectorId: Int,
    @JsonSerialize(using = InstantSerializer::class)
    @JsonDeserialize(using = InstantDeserializer::class)
    val expiryDate: Instant,
    val idTag: String,
    val reservationId: Int,
    val parentIdTag: String? = null
)