package com.izivia.ocpp.core20.model.common

import com.fasterxml.jackson.databind.annotation.JsonDeserialize
import com.fasterxml.jackson.databind.annotation.JsonSerialize
import com.izivia.ocpp.core20.model.common.ComponentType
import com.izivia.ocpp.core20.model.common.MessageContentType
import com.izivia.ocpp.core20.model.common.enumeration.MessagePriorityEnumType
import com.izivia.ocpp.core20.model.common.enumeration.MessageStateEnumType
import com.izivia.ocpp.utils.InstantDeserializer
import com.izivia.ocpp.utils.InstantSerializer
import kotlinx.datetime.Instant

data class MessageInfoType(
        val id: Int,
        val priority: MessagePriorityEnumType,
        val state: MessageStateEnumType? = null,
        @JsonSerialize(using = InstantSerializer::class)
    @JsonDeserialize(using = InstantDeserializer::class)
    val startDateTime: Instant? = null,
        @JsonSerialize(using = InstantSerializer::class)
    @JsonDeserialize(using = InstantDeserializer::class)
    val endDateTime: Instant? = null,
        val transactionId: String? = null,
        val message: MessageContentType,
        val display: ComponentType? = null
)