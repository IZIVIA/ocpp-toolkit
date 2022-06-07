package com.izivia.ocpp.api.model.common

import com.izivia.ocpp.api.model.remotestart.enumeration.ChargingProfileKindEnumType
import com.izivia.ocpp.api.model.common.enumeration.ChargingProfilePurposeEnumType
import com.izivia.ocpp.api.model.remotestart.enumeration.RecurrencyKindEnumType
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