package com.izivia.ocpp.core15.model.starttransaction

import com.izivia.ocpp.core15.model.Request
import com.izivia.ocpp.utils.HasActionTimestamp
import kotlin.time.Instant

data class StartTransactionReq(
    val connectorId: Int,
    val idTag: String,
    val meterStart: Int,
    val reservationId: Int? = null,
    override val timestamp: Instant
) : HasActionTimestamp, Request
