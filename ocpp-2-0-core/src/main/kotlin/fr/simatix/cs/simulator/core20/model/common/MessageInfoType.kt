package fr.simatix.cs.simulator.core20.model.common

import com.fasterxml.jackson.databind.annotation.JsonDeserialize
import com.fasterxml.jackson.databind.annotation.JsonSerialize
import fr.simatix.cs.simulator.core20.model.common.ComponentType
import fr.simatix.cs.simulator.core20.model.common.MessageContentType
import fr.simatix.cs.simulator.core20.model.common.enumeration.MessagePriorityEnumType
import fr.simatix.cs.simulator.core20.model.common.enumeration.MessageStateEnumType
import fr.simatix.cs.simulator.utils.InstantDeserializer
import fr.simatix.cs.simulator.utils.InstantSerializer
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