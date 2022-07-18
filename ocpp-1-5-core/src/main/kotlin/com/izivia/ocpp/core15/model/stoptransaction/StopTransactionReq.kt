package com.izivia.ocpp.core15.model.stoptransaction

import com.izivia.ocpp.core15.model.common.MeterValue
import kotlinx.datetime.Instant

data class StopTransactionReq(
    val idTag: String? = null,
    val meterStop: Int,
    val timestamp: Instant,
    val transactionId: Int,
    val transactionData: List<MeterValue>? = null
)
