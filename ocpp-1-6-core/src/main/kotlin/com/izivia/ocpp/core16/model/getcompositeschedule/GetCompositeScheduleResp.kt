package com.izivia.ocpp.core16.model.getcompositeschedule

import com.fasterxml.jackson.databind.annotation.JsonDeserialize
import com.fasterxml.jackson.databind.annotation.JsonSerialize
import com.izivia.ocpp.core16.model.common.ChargingSchedule
import com.izivia.ocpp.core16.model.getcompositeschedule.enumeration.GetCompositeScheduleStatus
import com.izivia.ocpp.utils.InstantDeserializer
import com.izivia.ocpp.utils.InstantSerializer
import kotlinx.datetime.Instant

data class GetCompositeScheduleResp(
    val status: GetCompositeScheduleStatus,
    val connectorId: Int? = null,
    @JsonSerialize(using = InstantSerializer::class)
    @JsonDeserialize(using = InstantDeserializer::class)
    val scheduleStart: Instant? = null,
    val chargingSchedule: ChargingSchedule? = null
)
