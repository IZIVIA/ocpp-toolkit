package com.izivia.ocpp.api.model.common

import com.izivia.ocpp.api.model.common.enumeration.MessagePriorityEnumType
import com.izivia.ocpp.api.model.common.enumeration.MessageStateEnumType
import kotlinx.datetime.Instant

data class MessageInfoType(
    val id: Int,
    val priority: MessagePriorityEnumType,
    val state: MessageStateEnumType? = null,
    val startDateTime: Instant? = null,
    val endDateTime: Instant? = null,
    val transactionId: String? = null,
    val message: MessageContentType,
    val display: ComponentType? = null
)