package fr.simatix.cs.simulator.core20.model.statusnotification

import com.fasterxml.jackson.databind.annotation.JsonDeserialize
import com.fasterxml.jackson.databind.annotation.JsonSerialize
import fr.simatix.cs.simulator.core20.model.statusnotification.enumeration.ConnectorStatusEnumType
import fr.simatix.cs.simulator.utils.InstantDeserializer
import fr.simatix.cs.simulator.utils.InstantSerializer
import kotlinx.datetime.Instant

data class StatusNotificationReq(
    val connectorId: Int,
    val connectorStatus: ConnectorStatusEnumType,
    val evseId: Int,
    @JsonSerialize(using = InstantSerializer::class)
    @JsonDeserialize(using = InstantDeserializer::class)
    val timestamp: Instant
)