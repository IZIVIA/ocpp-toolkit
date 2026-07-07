package com.izivia.ocpp.core12.model.starttransaction

import com.izivia.ocpp.core12.model.Request
import com.izivia.ocpp.utils.HasActionTimestamp
import kotlinx.datetime.Instant

data class StartTransactionReq(
    val connectorId: Int,
    val idTag: String,
    val meterStart: Int,
    override val timestamp: Instant
) : HasActionTimestamp, Request
