package com.izivia.ocpp.core20.model.notifyevent

import kotlinx.datetime.Instant

data class NotifyEventReq(
    val generatedAt: Instant,
    val seqNo: Int,
    val eventData: List<EventDataType>,
    val tbc: Boolean = false
)