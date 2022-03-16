package fr.simatix.cs.simulator.core16.model.bootnotification

import com.fasterxml.jackson.databind.annotation.JsonDeserialize
import com.fasterxml.jackson.databind.annotation.JsonSerialize
import fr.simatix.cs.simulator.core16.model.bootnotification.enumeration.RegistrationStatus
import fr.simatix.cs.simulator.utils.InstantDeserializer
import fr.simatix.cs.simulator.utils.InstantSerializer
import kotlinx.datetime.Instant

data class BootNotificationResp(
    @JsonSerialize(using = InstantSerializer::class)
    @JsonDeserialize(using = InstantDeserializer::class)
    val currentTime: Instant,
    val interval: Int,
    val status: RegistrationStatus
)