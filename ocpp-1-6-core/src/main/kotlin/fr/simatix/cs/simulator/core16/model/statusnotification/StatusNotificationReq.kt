package fr.simatix.cs.simulator.core16.model.statusnotification

import com.fasterxml.jackson.databind.annotation.JsonDeserialize
import com.fasterxml.jackson.databind.annotation.JsonSerialize
import fr.simatix.cs.simulator.core16.model.statusnotification.enumeration.ChargePointStatus
import fr.simatix.cs.simulator.core16.model.statusnotification.enumeration.ChargePointErrorCode
import fr.simatix.cs.simulator.utils.InstantDeserializer
import fr.simatix.cs.simulator.utils.InstantSerializer
import kotlinx.datetime.Instant

data class StatusNotificationReq(
    val connectorId: Int,
    val errorCode: ChargePointErrorCode,
    val status: ChargePointStatus,
    val info: String? = null,
    @JsonSerialize(using = InstantSerializer::class)
    @JsonDeserialize(using = InstantDeserializer::class)
    val timestamp: Instant? = null,
    val vendorErrorCode: String? = null,
    val vendorId: String? = null
)