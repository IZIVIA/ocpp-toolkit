package com.izivia.ocpp.api

import com.izivia.ocpp.api.model.common.EVSEType
import com.izivia.ocpp.api.model.common.IdTokenType
import com.izivia.ocpp.api.model.common.MeterValueType
import com.izivia.ocpp.api.model.statusnotification.enumeration.ChargePointErrorCode
import com.izivia.ocpp.api.model.transactionevent.TransactionEventReq
import com.izivia.ocpp.api.model.transactionevent.TransactionType
import com.izivia.ocpp.api.model.transactionevent.enumeration.ChargingStateEnumType
import com.izivia.ocpp.api.model.transactionevent.enumeration.ReasonEnumType
import com.izivia.ocpp.api.model.transactionevent.enumeration.TransactionEventEnumType
import com.izivia.ocpp.api.model.transactionevent.enumeration.TriggerReasonEnumType
import kotlin.time.Clock
import kotlin.time.Instant

@DslMarker
annotation class TransactionDsl

@TransactionDsl
class TransactionEventReqBuilder {
    private var eventType: TransactionEventEnumType = TransactionEventEnumType.Started
    private var timestamp: Instant = Clock.System.now()
    private var triggerReason: TriggerReasonEnumType = TriggerReasonEnumType.Authorized
    private var seqNo: Int = 0
    private var transactionInfo: TransactionType = TransactionType("0")
    private var cableMaxCurrent: Int? = null
    private var evse: EVSEType? = null
    private var idToken: IdTokenType? = null
    private var meterValue: MutableList<MeterValueType>? = null
    private var numberOfPhasesUsed: Int? = null
    private var offline: Boolean? = null
    private var reservationId: Int? = null

    fun eventType(value: TransactionEventEnumType) {
        eventType = value
    }

    fun timestamp(value: Instant) {
        timestamp = value
    }

    fun triggerReason(value: TriggerReasonEnumType) {
        triggerReason = value
    }

    fun seqNo(value: Int) {
        seqNo = value
    }

    fun transactionInfo(builder: TransactionTypeBuilder.() -> Unit) {
        transactionInfo = TransactionTypeBuilder().apply(builder).build()
    }

    fun evse(builder: EVSETypeBuilder.() -> Unit) {
        evse = EVSETypeBuilder().apply(builder).build()
    }

    fun cableMaxCurrent(value: Int?) {
        cableMaxCurrent = value
    }

    fun numberOfPhasesUsed(value: Int?) {
        numberOfPhasesUsed = value
    }

    fun offline(value: Boolean?) {
        offline = value
    }

    fun reservationId(value: Int?) {
        reservationId = value
    }

    fun build() = TransactionEventReq(
        eventType = eventType,
        timestamp = timestamp,
        triggerReason = triggerReason,
        seqNo = seqNo,
        transactionInfo = transactionInfo,
        cableMaxCurrent = cableMaxCurrent,
        evse = evse,
        idToken = idToken,
        meterValue = meterValue,
        numberOfPhasesUsed = numberOfPhasesUsed,
        offline = offline,
        reservationId = reservationId
    )
}

fun transactionEventReq(builder: TransactionEventReqBuilder.() -> Unit): TransactionEventReq =
    TransactionEventReqBuilder().apply(builder).build()

@TransactionDsl
class TransactionTypeBuilder {
    private var transactionId: String? = null
    private var chargingState: ChargingStateEnumType? = null
    private var timeSpentCharging: Int? = null
    private var stoppedReason: ReasonEnumType? = null
    private var remoteStartId: Int? = null
    private var errorCode: ChargePointErrorCode = ChargePointErrorCode.NoError

    fun transactionId(value: String) {
        transactionId = value
    }

    fun chargingState(value: ChargingStateEnumType?) {
        chargingState = value
    }

    fun timeSpentCharging(value: Int?) {
        timeSpentCharging = value
    }

    fun stoppedReason(value: ReasonEnumType?) {
        stoppedReason = value
    }

    fun remoteStartId(value: Int?) {
        remoteStartId = value
    }

    fun errorCode(value: ChargePointErrorCode) {
        errorCode = value
    }

    fun build() = TransactionType(
        transactionId = requireNotNull(transactionId) { "TransactionType.transactionId is required" },
        chargingState = chargingState,
        timeSpentCharging = timeSpentCharging,
        stoppedReason = stoppedReason,
        remoteStartId = remoteStartId,
        errorCode = errorCode
    )
}

@TransactionDsl
class EVSETypeBuilder {
    private var id: Int = 1
    private var connectorId: Int? = null

    fun id(value: Int) {
        id = value
    }

    fun connectorId(value: Int?) {
        connectorId = value
    }

    fun build() = EVSEType(
        id = id,
        connectorId = connectorId
    )
}
