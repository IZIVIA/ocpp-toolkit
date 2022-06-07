package com.izivia.ocpp.api.model.unlockconnector

import com.izivia.ocpp.api.model.Response
import com.izivia.ocpp.api.model.common.StatusInfoType
import com.izivia.ocpp.api.model.unlockconnector.enumeration.UnlockStatusEnumType

data class UnlockConnectorResp(
    val status: UnlockStatusEnumType,
    val statusInfo: StatusInfoType? = null
): Response