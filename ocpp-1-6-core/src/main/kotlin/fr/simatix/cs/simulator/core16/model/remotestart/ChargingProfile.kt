package fr.simatix.cs.simulator.core16.model.remotestart

import com.fasterxml.jackson.databind.annotation.JsonDeserialize
import com.fasterxml.jackson.databind.annotation.JsonSerialize
import fr.simatix.cs.simulator.core16.model.remotestart.enumeration.ChargingProfileKindType
import fr.simatix.cs.simulator.core16.model.remotestart.enumeration.ChargingProfilePurposeType
import fr.simatix.cs.simulator.core16.model.remotestart.enumeration.RecurrencyKindType
import fr.simatix.cs.simulator.utils.InstantDeserializer
import fr.simatix.cs.simulator.utils.InstantSerializer
import kotlinx.datetime.Instant

data class ChargingProfile(
    val chargingProfileId: Int,
    val stackLevel: Int,
    val chargingProfilePurpose: ChargingProfilePurposeType,
    val chargingProfileKind: ChargingProfileKindType,
    val chargingSchedule: ChargingSchedule,
    val recurrencyKind: RecurrencyKindType? = null,
    val transactionId: Int? = null,
    @JsonSerialize(using = InstantSerializer::class)
    @JsonDeserialize(using = InstantDeserializer::class)
    val validFrom: Instant? = null,
    @JsonSerialize(using = InstantSerializer::class)
    @JsonDeserialize(using = InstantDeserializer::class)
    val validTo: Instant? = null
)