package com.izivia.ocpp.core20.model.notifyevent

import com.izivia.ocpp.core20.model.common.ComponentType
import com.izivia.ocpp.core20.model.common.VariableType
import com.izivia.ocpp.core20.model.notifyevent.enumeration.EventNotificationEnumType
import com.izivia.ocpp.core20.model.notifyevent.enumeration.EventTriggerEnumType
import kotlinx.datetime.Instant

data class EventDataType(
    val eventId: Int,
    val timestamp: Instant,
    val trigger: EventTriggerEnumType,
    val actualValue: String,
    val eventNotificationType: EventNotificationEnumType,
    val component: ComponentType,
    val variable: VariableType,
    val cause: Int? = null,
    val techCode: String? = null,
    val techInfo: String? = null,
    val cleared: Boolean? = null,
    val transactionId: String? = null,
    val variableMonitoringId: Int? = null
)