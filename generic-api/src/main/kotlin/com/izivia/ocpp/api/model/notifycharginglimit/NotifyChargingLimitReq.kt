package com.izivia.ocpp.api.model.notifycharginglimit

import com.izivia.ocpp.api.model.Request
import com.izivia.ocpp.api.model.common.ChargingScheduleType

data class NotifyChargingLimitReq(
    val chargingLimit: ChargingLimitType,
    val evseId: Int? = null,
    val chargingSchedule: List<ChargingScheduleType>? = null
): Request
