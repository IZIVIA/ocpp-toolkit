package com.izivia.ocpp.api.model.notifyevent

import com.izivia.ocpp.api.model.Request
import kotlin.time.Instant

data class NotifyEventReq(
    val generatedAt: Instant,
    val seqNo: Int,
    val eventData: List<EventDataType>,
    val tbc: Boolean = false
): Request