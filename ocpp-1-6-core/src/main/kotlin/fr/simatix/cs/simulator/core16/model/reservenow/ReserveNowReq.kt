package fr.simatix.cs.simulator.core16.model.reservenow

import com.fasterxml.jackson.databind.annotation.JsonDeserialize
import com.fasterxml.jackson.databind.annotation.JsonSerialize
import fr.simatix.cs.simulator.utils.InstantDeserializer
import fr.simatix.cs.simulator.utils.InstantSerializer
import kotlinx.datetime.Instant

data class ReserveNowReq(
    val connectorId: Int,
    @JsonSerialize(using = InstantSerializer::class)
    @JsonDeserialize(using = InstantDeserializer::class)
    val expiryDate: Instant,
    val idTag: String,
    val parentIdTag: String? = null,
    val reservationId: Int
)