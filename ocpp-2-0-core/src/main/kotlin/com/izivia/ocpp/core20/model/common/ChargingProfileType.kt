package com.izivia.ocpp.core20.model.common

import com.fasterxml.jackson.databind.annotation.JsonDeserialize
import com.fasterxml.jackson.databind.annotation.JsonSerialize
import com.izivia.ocpp.core20.model.remotestart.enumeration.ChargingProfileKindEnumType
import com.izivia.ocpp.core20.model.common.enumeration.ChargingProfilePurposeEnumType
import com.izivia.ocpp.core20.model.remotestart.enumeration.RecurrencyKindEnumType
import com.izivia.ocpp.utils.InstantDeserializer
import com.izivia.ocpp.utils.InstantSerializer
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