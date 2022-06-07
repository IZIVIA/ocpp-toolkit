package com.izivia.ocpp.api.model.notifyevchargingneeds

import com.izivia.ocpp.api.model.Response
import com.izivia.ocpp.api.model.common.StatusInfoType
import com.izivia.ocpp.api.model.notifyevchargingneeds.enumeration.NotifyEVChargingNeedsStatusEnumType

data class NotifyEVChargingNeedsResp(
    val status: NotifyEVChargingNeedsStatusEnumType,
    val statusInfo: StatusInfoType? = null
): Response