package com.izivia.ocpp.core20.model.notifyevent

import com.izivia.ocpp.utils.HasActionTimestamp
import kotlin.time.Instant

data class NotifyEventReq(
    val generatedAt: Instant,
    val seqNo: Int,
    val eventData: List<EventDataType>,
    val tbc: Boolean = false
) : HasActionTimestamp {
    override val timestamp: Instant
        get() = generatedAt
}