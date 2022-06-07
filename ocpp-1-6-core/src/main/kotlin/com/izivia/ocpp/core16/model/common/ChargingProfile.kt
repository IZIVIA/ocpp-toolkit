package com.izivia.ocpp.core16.model.common

import com.fasterxml.jackson.databind.annotation.JsonDeserialize
import com.fasterxml.jackson.databind.annotation.JsonSerialize
import com.izivia.ocpp.core16.model.common.ChargingSchedule
import com.izivia.ocpp.core16.model.remotestart.enumeration.ChargingProfileKindType
import com.izivia.ocpp.core16.model.common.enumeration.ChargingProfilePurposeType
import com.izivia.ocpp.core16.model.remotestart.enumeration.RecurrencyKindType
import com.izivia.ocpp.utils.InstantDeserializer
import com.izivia.ocpp.utils.InstantSerializer
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