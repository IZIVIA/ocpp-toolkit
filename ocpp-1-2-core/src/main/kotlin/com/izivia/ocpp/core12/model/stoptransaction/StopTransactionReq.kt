package com.izivia.ocpp.core12.model.stoptransaction

import com.izivia.ocpp.core12.model.Request
import com.izivia.ocpp.utils.HasActionTimestamp
import kotlin.time.Instant

data class StopTransactionReq(
    val transactionId: Int,
    val idTag: String? = null,
    override val timestamp: Instant,
    val meterStop: Int
) : HasActionTimestamp, Request
