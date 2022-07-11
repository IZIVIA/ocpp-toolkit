package com.izivia.ocpp.core16.model.getcompositeschedule

import com.izivia.ocpp.core16.model.common.ChargingSchedule
import com.izivia.ocpp.core16.model.getcompositeschedule.enumeration.GetCompositeScheduleStatus
import kotlinx.datetime.Instant

data class GetCompositeScheduleResp(
    val status: GetCompositeScheduleStatus,
    val connectorId: Int? = null,
    val scheduleStart: Instant? = null,
    val chargingSchedule: ChargingSchedule? = null
)
