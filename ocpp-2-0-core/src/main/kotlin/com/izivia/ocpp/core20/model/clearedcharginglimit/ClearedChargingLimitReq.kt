package com.izivia.ocpp.core20.model.clearedcharginglimit

import com.izivia.ocpp.core20.model.common.enumeration.ChargingLimitSourceEnumType


data class ClearedChargingLimitReq (
    val chargingLimitSource: ChargingLimitSourceEnumType,
    val evseId: Int? = null
)
