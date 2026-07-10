package com.izivia.ocpp.core20.model.notifyevchargingschedule

import com.izivia.ocpp.core20.model.common.ChargingScheduleType
import kotlin.time.Instant

data class NotifyEVChargingScheduleReq(
    val timeBase: Instant,
    val evseId: Int,
    val chargingSchedule: ChargingScheduleType
)