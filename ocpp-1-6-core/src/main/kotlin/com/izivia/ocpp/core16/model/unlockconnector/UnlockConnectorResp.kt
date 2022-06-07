package com.izivia.ocpp.core16.model.unlockconnector

import com.izivia.ocpp.core16.model.unlockconnector.enumeration.UnlockStatus

data class UnlockConnectorResp(
    val status: UnlockStatus
)