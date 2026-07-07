package com.izivia.ocpp.core12.model.unlockconnector

import com.izivia.ocpp.core12.model.Request

data class UnlockConnectorReq(
    val connectorId: Int
): Request
