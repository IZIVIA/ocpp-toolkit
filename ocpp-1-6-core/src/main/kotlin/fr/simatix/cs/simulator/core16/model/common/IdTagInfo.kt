package fr.simatix.cs.simulator.core16.model.common

import com.fasterxml.jackson.databind.annotation.JsonDeserialize
import com.fasterxml.jackson.databind.annotation.JsonSerialize
import fr.simatix.cs.simulator.core16.model.common.enumeration.AuthorizationStatus
import fr.simatix.cs.simulator.utils.InstantDeserializer
import fr.simatix.cs.simulator.utils.InstantSerializer
import kotlinx.datetime.Instant

data class IdTagInfo(
    val status: AuthorizationStatus,
    @JsonSerialize(using = InstantSerializer::class)
    @JsonDeserialize(using = InstantDeserializer::class)
    val expiryDate: Instant? = null,
    val parentIdTag: String? = null
)