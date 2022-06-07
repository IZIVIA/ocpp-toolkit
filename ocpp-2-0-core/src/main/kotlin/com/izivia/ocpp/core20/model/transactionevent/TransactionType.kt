package com.izivia.ocpp.core20.model.transactionevent

import com.izivia.ocpp.core20.model.transactionevent.enumeration.ChargingStateEnumType
import com.izivia.ocpp.core20.model.transactionevent.enumeration.ReasonEnumType

data class TransactionType(
    val transactionId: String,
    val chargingState: ChargingStateEnumType? = null,
    val timeSpentCharging: Int? = null,
    val stoppedReason: ReasonEnumType? = null,
    val remoteStartId: Int? = null
)
