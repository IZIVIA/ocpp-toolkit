package com.izivia.ocpp.api.model.getcompositeschedule

import com.izivia.ocpp.api.model.Request
import com.izivia.ocpp.api.model.common.enumeration.ChargingRateUnitEnumType

data class GetCompositeScheduleReq(
    val evseId: Int,
    val duration: Int,
    val chargingRateUnit: ChargingRateUnitEnumType? = null
): Request
