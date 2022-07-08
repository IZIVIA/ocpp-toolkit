package com.izivia.ocpp.core15.model.starttransaction

import kotlinx.datetime.Instant

data class StartTransactionReq(
    val connectorId: Int,
    val idTag: String,
    val meterStart: Int,
    val reservationId: Int? = null,
    val timestamp: Instant
)
