package com.izivia.ocpp.core20.model.notifycharginglimit

import com.izivia.ocpp.core20.model.common.ChargingScheduleType

data class NotifyChargingLimitReq(
    val chargingLimit: ChargingLimitType,
    val evseId: Int? = null,
    val chargingSchedule: List<ChargingScheduleType>? = null
)