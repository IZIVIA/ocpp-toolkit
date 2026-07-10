package com.izivia.ocpp.core16.model.getcompositeschedule

import com.izivia.ocpp.core16.model.Response
import com.izivia.ocpp.core16.model.common.ChargingSchedule
import com.izivia.ocpp.core16.model.getcompositeschedule.enumeration.GetCompositeScheduleStatus
import kotlin.time.Instant

data class GetCompositeScheduleResp(
    val status: GetCompositeScheduleStatus,
    val connectorId: Int? = null,
    val scheduleStart: Instant? = null,
    val chargingSchedule: ChargingSchedule? = null
) : Response
