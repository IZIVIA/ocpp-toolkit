package com.izivia.ocpp.api.model.transactionevent

import com.izivia.ocpp.api.model.statusnotification.enumeration.ChargePointErrorCode
import com.izivia.ocpp.api.model.transactionevent.enumeration.ChargingStateEnumType
import com.izivia.ocpp.api.model.transactionevent.enumeration.ReasonEnumType

data class TransactionType(
    val transactionId: String,
    val chargingState: ChargingStateEnumType? = null,
    val timeSpentCharging: Int? = null,
    val stoppedReason: ReasonEnumType? = null,
    val remoteStartId: Int? = null,
    val errorCode: ChargePointErrorCode = ChargePointErrorCode.NoError
)
