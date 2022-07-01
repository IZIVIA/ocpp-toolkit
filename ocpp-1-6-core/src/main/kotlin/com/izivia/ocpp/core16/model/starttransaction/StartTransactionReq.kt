package com.izivia.ocpp.core16.model.starttransaction

import kotlinx.datetime.Instant

data class StartTransactionReq(
    val connectorId: Int,
    val idTag: String,
    val meterStart: Int,
    val timestamp: Instant,
    val reservationId: Int? = null
)