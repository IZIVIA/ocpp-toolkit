package fr.simatix.cs.simulator.api.model.notifyevent

import fr.simatix.cs.simulator.api.model.Request
import kotlinx.datetime.Instant

data class NotifyEventReq(
    val generatedAt: Instant,
    val seqNo: Int,
    val eventData: List<EventDataType>,
    val tbc: Boolean = false
): Request