package com.izivia.ocpp.core20.model.notifyevchargingneeds

import com.izivia.ocpp.core20.model.common.StatusInfoType
import com.izivia.ocpp.core20.model.notifyevchargingneeds.enumeration.NotifyEVChargingNeedsStatusEnumType

data class NotifyEVChargingNeedsResp(
    val status: NotifyEVChargingNeedsStatusEnumType,
    val statusInfo: StatusInfoType? = null
)