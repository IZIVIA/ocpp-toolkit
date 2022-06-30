package com.izivia.ocpp.core15.model.unlockconnector

import com.izivia.ocpp.core15.model.unlockconnector.enumeration.UnlockStatus

data class UnlockConnectorResp(
    val status: UnlockStatus
)