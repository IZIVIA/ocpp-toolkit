package com.izivia.ocpp.core12.model.unlockconnector

import com.izivia.ocpp.core12.model.Response
import com.izivia.ocpp.core12.model.unlockconnector.enumeration.UnlockStatus

data class UnlockConnectorResp(
    val status: UnlockStatus
): Response
