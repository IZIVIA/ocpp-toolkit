package com.izivia.ocpp.core20.model.notifycharginglimit

import com.izivia.ocpp.core20.model.common.enumeration.ChargingLimitSourceEnumType

data class ChargingLimitType(
    val chargingLimitSource: ChargingLimitSourceEnumType,
    val isGridCritical: Boolean? = null
)