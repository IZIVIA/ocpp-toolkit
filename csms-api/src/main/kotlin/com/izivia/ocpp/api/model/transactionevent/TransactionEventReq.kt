package com.izivia.ocpp.api.model.transactionevent

import com.izivia.ocpp.api.model.Request
import com.izivia.ocpp.api.model.common.EVSEType
import com.izivia.ocpp.api.model.common.IdTokenType
import com.izivia.ocpp.api.model.common.MeterValueType
import com.izivia.ocpp.api.model.transactionevent.enumeration.TransactionEventEnumType
import com.izivia.ocpp.api.model.transactionevent.enumeration.TriggerReasonEnumType
import kotlinx.datetime.Instant

data class TransactionEventReq(
    val eventType: TransactionEventEnumType,
    var timestamp: Instant,
    val triggerReason: TriggerReasonEnumType,
    val seqNo: Int,
    val transactionInfo: TransactionType,
    val cableMaxCurrent: Int? = null,
    val evse: EVSEType? = null,
    val idToken: IdTokenType? = null,
    val meterValue: List<MeterValueType>? = null,
    val numberOfPhasesUsed: Int? = null,
    val offline: Boolean? = false,
    val reservationId: Int? = null,
): Request