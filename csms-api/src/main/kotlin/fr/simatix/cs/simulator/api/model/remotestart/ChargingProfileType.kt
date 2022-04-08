package fr.simatix.cs.simulator.api.model.remotestart

import fr.simatix.cs.simulator.api.model.remotestart.enumeration.ChargingProfileKindEnumType
import fr.simatix.cs.simulator.api.model.common.enumeration.ChargingProfilePurposeEnumType
import fr.simatix.cs.simulator.api.model.remotestart.enumeration.RecurrencyKindEnumType
import kotlinx.datetime.Instant


data class ChargingProfileType(
    val id: Int,
    val stackLevel: Int,
    val chargingProfilePurpose: ChargingProfilePurposeEnumType,
    val chargingProfileKind: ChargingProfileKindEnumType,
    val chargingSchedule: List<ChargingScheduleType>,
    val recurrencyKind: RecurrencyKindEnumType? = null,
    val validFrom: Instant? = null,
    val validTo: Instant? = null,
    val transactionId: String? = null
)