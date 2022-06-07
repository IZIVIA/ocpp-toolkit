package com.izivia.ocpp.api.model.clearedcharginglimit

import com.izivia.ocpp.api.model.Request
import com.izivia.ocpp.api.model.common.enumeration.ChargingLimitSourceEnumType

data class ClearedChargingLimitReq (
    val chargingLimitSource: ChargingLimitSourceEnumType,
    val evseId: Int? = null
): Request
