package com.izivia.ocpp.core15.model.unlockconnector

import com.izivia.ocpp.core15.model.Request

data class UnlockConnectorReq(
    val connectorId: Int
): Request
