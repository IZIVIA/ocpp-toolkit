package fr.simatix.cs.simulator.api.model.notifydisplaymessages

import fr.simatix.cs.simulator.api.model.common.ComponentType
import fr.simatix.cs.simulator.api.model.common.MessageContentType
import fr.simatix.cs.simulator.api.model.notifydisplaymessages.enumeration.MessagePriorityEnumType
import fr.simatix.cs.simulator.api.model.notifydisplaymessages.enumeration.MessageStateEnumType
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