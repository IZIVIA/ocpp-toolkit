package com.izivia.ocpp.core16.model.starttransaction

import com.izivia.ocpp.core16.model.Request
import com.izivia.ocpp.utils.HasActionTimestamp
import kotlin.time.Instant

data class StartTransactionReq(
    val connectorId: Int,
    val idTag: String,
    val meterStart: Int,
    override val timestamp: Instant,
    val reservationId: Int? = null
) : HasActionTimestamp, Request
