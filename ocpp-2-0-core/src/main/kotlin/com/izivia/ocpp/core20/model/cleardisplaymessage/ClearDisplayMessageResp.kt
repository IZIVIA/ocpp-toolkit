package com.izivia.ocpp.core20.model.cleardisplaymessage

import com.izivia.ocpp.core20.model.cleardisplaymessage.enumeration.ClearMessageStatusEnumType
import com.izivia.ocpp.core20.model.common.StatusInfoType

data class ClearDisplayMessageResp(
    val status: ClearMessageStatusEnumType,
    val statusInfo: StatusInfoType? = null
)