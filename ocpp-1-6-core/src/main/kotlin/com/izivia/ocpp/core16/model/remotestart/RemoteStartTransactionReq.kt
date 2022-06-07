package com.izivia.ocpp.core16.model.remotestart

import com.izivia.ocpp.core16.model.common.ChargingProfile

data class RemoteStartTransactionReq(
    val idTag: String,
    val connectorId: Int? = null,
    val chargingProfile: ChargingProfile? = null
)