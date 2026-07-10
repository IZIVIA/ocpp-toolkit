package com.izivia.ocpp.core16.model.stoptransaction

import com.izivia.ocpp.core16.model.Request
import com.izivia.ocpp.core16.model.common.MeterValue
import com.izivia.ocpp.core16.model.stoptransaction.enumeration.Reason
import com.izivia.ocpp.utils.HasActionTimestamp
import kotlin.time.Instant

data class StopTransactionReq(
    val meterStop: Int,
    override val timestamp: Instant,
    val transactionId: Int,
    val idTag: String? = null,
    val reason: Reason? = null,
    val transactionData: List<MeterValue>? = null
) : HasActionTimestamp, Request
