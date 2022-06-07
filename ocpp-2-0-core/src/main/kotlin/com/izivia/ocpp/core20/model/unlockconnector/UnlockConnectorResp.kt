package com.izivia.ocpp.core20.model.unlockconnector

import com.izivia.ocpp.core20.model.common.StatusInfoType
import com.izivia.ocpp.core20.model.unlockconnector.enumeration.UnlockStatusEnumType

data class UnlockConnectorResp(
    val status: UnlockStatusEnumType,
    val statusInfo: StatusInfoType? = null
)