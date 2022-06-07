package com.izivia.ocpp.core20.model.notifyevchargingschedule

import com.fasterxml.jackson.databind.annotation.JsonDeserialize
import com.fasterxml.jackson.databind.annotation.JsonSerialize
import com.izivia.ocpp.core20.model.common.ChargingScheduleType
import com.izivia.ocpp.utils.InstantDeserializer
import com.izivia.ocpp.utils.InstantSerializer
import kotlinx.datetime.Instant

data class NotifyEVChargingScheduleReq(
    @JsonSerialize(using = InstantSerializer::class)
    @JsonDeserialize(using = InstantDeserializer::class)
    val timeBase: Instant,
    val evseId: Int,
    val chargingSchedule: ChargingScheduleType
)