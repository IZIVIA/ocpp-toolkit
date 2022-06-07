package com.izivia.ocpp.api.model.notifycharginglimit

import com.izivia.ocpp.api.model.common.enumeration.ChargingLimitSourceEnumType

data class ChargingLimitType(
    val chargingLimitSource: ChargingLimitSourceEnumType,
    val isGridCritical: Boolean? = null
)
