package fr.simatix.cs.simulator.api.model.transactionevent

import fr.simatix.cs.simulator.api.model.Request
import fr.simatix.cs.simulator.api.model.common.EVSEType
import fr.simatix.cs.simulator.api.model.common.IdTokenType
import fr.simatix.cs.simulator.api.model.common.MeterValueType
import fr.simatix.cs.simulator.api.model.transactionevent.enumeration.TransactionEventEnumType
import fr.simatix.cs.simulator.api.model.transactionevent.enumeration.TriggerReasonEnumType
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