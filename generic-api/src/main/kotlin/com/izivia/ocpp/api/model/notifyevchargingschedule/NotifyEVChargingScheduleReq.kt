package com.izivia.ocpp.api.model.notifyevchargingschedule

import com.izivia.ocpp.api.model.Request
import com.izivia.ocpp.api.model.common.ChargingScheduleType
import kotlinx.datetime.Instant

data class NotifyEVChargingScheduleReq(
    val timeBase: Instant,
    val evseId: Int,
    val chargingSchedule: ChargingScheduleType
): Request