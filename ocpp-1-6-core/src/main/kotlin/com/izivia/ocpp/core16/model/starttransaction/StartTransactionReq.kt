package com.izivia.ocpp.core16.model.starttransaction

import com.fasterxml.jackson.databind.annotation.JsonDeserialize
import com.fasterxml.jackson.databind.annotation.JsonSerialize
import com.izivia.ocpp.utils.InstantDeserializer
import com.izivia.ocpp.utils.InstantSerializer
import kotlinx.datetime.Instant

data class StartTransactionReq(
    val connectorId: Int,
    val idTag: String,
    val meterStart: Int,
    @JsonSerialize(using = InstantSerializer::class)
    @JsonDeserialize(using = InstantDeserializer::class)
    val timestamp: Instant,
    val reservationId: Int? = null
)