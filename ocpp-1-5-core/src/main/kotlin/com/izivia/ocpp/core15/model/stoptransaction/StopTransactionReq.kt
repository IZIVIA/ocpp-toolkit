package com.izivia.ocpp.core15.model.stoptransaction

import com.izivia.ocpp.core15.model.Request
import com.izivia.ocpp.core15.model.common.MeterValue
import com.izivia.ocpp.utils.HasActionTimestamp
import kotlinx.datetime.Instant

data class StopTransactionReq(
    val idTag: String? = null,
    val meterStop: Int,
    override val timestamp: Instant,
    val transactionId: Int,
    val transactionData: List<TransactionData>? = null
) : HasActionTimestamp, Request

data class TransactionData(
    val values: List<MeterValue>? = null
)
