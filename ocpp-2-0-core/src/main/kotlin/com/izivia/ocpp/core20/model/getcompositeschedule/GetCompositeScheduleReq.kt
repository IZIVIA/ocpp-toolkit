package com.izivia.ocpp.core20.model.getcompositeschedule

import com.izivia.ocpp.core20.model.common.enumeration.ChargingRateUnitEnumType

data class GetCompositeScheduleReq(
    val evseId: Int,
    val duration: Int,
    val chargingRateUnit: ChargingRateUnitEnumType? = null
)
