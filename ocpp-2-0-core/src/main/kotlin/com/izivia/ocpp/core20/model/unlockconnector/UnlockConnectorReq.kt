package com.izivia.ocpp.core20.model.unlockconnector

data class UnlockConnectorReq(
    val connectorId: Int,
    val evseId: Int
)