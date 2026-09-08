package com.izivia.ocpp.api.model.notifyevchargingschedule

import com.izivia.ocpp.api.model.Request
import com.izivia.ocpp.api.model.common.ChargingScheduleType
import kotlin.time.Instant

data class NotifyEVChargingScheduleReq(
    val timeBase: Instant,
    val evseId: Int,
    val chargingSchedule: ChargingScheduleType
): Request