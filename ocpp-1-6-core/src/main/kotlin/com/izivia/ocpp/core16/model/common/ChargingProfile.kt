package com.izivia.ocpp.core16.model.common

import com.izivia.ocpp.core16.model.common.enumeration.ChargingProfilePurposeType
import com.izivia.ocpp.core16.model.remotestart.enumeration.ChargingProfileKindType
import com.izivia.ocpp.core16.model.remotestart.enumeration.RecurrencyKindType
import kotlinx.datetime.Instant

data class ChargingProfile(
    val chargingProfileId: Int,
    val stackLevel: Int,
    val chargingProfilePurpose: ChargingProfilePurposeType,
    val chargingProfileKind: ChargingProfileKindType,
    val chargingSchedule: ChargingSchedule,
    val recurrencyKind: RecurrencyKindType? = null,
    val transactionId: Int? = null,
    val validFrom: Instant? = null,
    val validTo: Instant? = null
)