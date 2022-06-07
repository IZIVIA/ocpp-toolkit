package com.izivia.ocpp.core20.model.notifyevent

import com.fasterxml.jackson.databind.annotation.JsonDeserialize
import com.fasterxml.jackson.databind.annotation.JsonSerialize
import com.izivia.ocpp.utils.InstantDeserializer
import com.izivia.ocpp.utils.InstantSerializer
import kotlinx.datetime.Instant

data class NotifyEventReq(
    @JsonSerialize(using = InstantSerializer::class)
    @JsonDeserialize(using = InstantDeserializer::class)
    val generatedAt: Instant,
    val seqNo: Int,
    val eventData: List<EventDataType>,
    val tbc: Boolean = false
)