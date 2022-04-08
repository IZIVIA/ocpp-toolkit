package fr.simatix.cs.simulator.core20.model.remotestart

import com.fasterxml.jackson.databind.annotation.JsonDeserialize
import com.fasterxml.jackson.databind.annotation.JsonSerialize
import fr.simatix.cs.simulator.core20.model.remotestart.enumeration.ChargingProfileKindEnumType
import fr.simatix.cs.simulator.core20.model.common.enumeration.ChargingProfilePurposeEnumType
import fr.simatix.cs.simulator.core20.model.remotestart.enumeration.RecurrencyKindEnumType
import fr.simatix.cs.simulator.utils.InstantDeserializer
import fr.simatix.cs.simulator.utils.InstantSerializer
import kotlinx.datetime.Instant

data class ChargingProfileType(
    val id: Int,
    val stackLevel: Int,
    val chargingProfilePurpose: ChargingProfilePurposeEnumType,
    val chargingProfileKind: ChargingProfileKindEnumType,
    val chargingSchedule: List<ChargingScheduleType>,
    val recurrencyKind: RecurrencyKindEnumType? = null,
    @JsonSerialize(using = InstantSerializer::class)
    @JsonDeserialize(using = InstantDeserializer::class)
    val validFrom: Instant? = null,
    @JsonSerialize(using = InstantSerializer::class)
    @JsonDeserialize(using = InstantDeserializer::class)
    val validTo: Instant? = null,
    val transactionId: String? = null
)