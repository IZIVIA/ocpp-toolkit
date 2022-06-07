package com.izivia.ocpp.api.model.unlockconnector

import com.izivia.ocpp.api.model.Request

data class UnlockConnectorReq(
    val connectorId: Int,
    val evseId: Int
): Request