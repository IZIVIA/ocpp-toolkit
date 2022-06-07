package com.izivia.ocpp.api.model.notifyevchargingneeds

import com.izivia.ocpp.api.model.Request

data class NotifyEVChargingNeedsReq(
    val evseId: Int,
    val chargingNeeds: ChargingNeedsType,
    val maxScheduleTuples: Int? = null
): Request