package fr.simatix.cs.simulator.core20.model.notifyevent

import com.fasterxml.jackson.databind.annotation.JsonDeserialize
import com.fasterxml.jackson.databind.annotation.JsonSerialize
import fr.simatix.cs.simulator.core20.model.common.ComponentType
import fr.simatix.cs.simulator.core20.model.common.VariableType
import fr.simatix.cs.simulator.core20.model.notifyevent.enumeration.EventNotificationEnumType
import fr.simatix.cs.simulator.core20.model.notifyevent.enumeration.EventTriggerEnumType
import fr.simatix.cs.simulator.utils.InstantDeserializer
import fr.simatix.cs.simulator.utils.InstantSerializer
import kotlinx.datetime.Instant

data class EventDataType(
    val eventId: Int,
    @JsonSerialize(using = InstantSerializer::class)
    @JsonDeserialize(using = InstantDeserializer::class)
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