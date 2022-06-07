package com.izivia.ocpp.api.model.cleardisplaymessage

import com.izivia.ocpp.api.model.Response
import com.izivia.ocpp.api.model.cleardisplaymessage.enumeration.ClearMessageStatusEnumType
import com.izivia.ocpp.api.model.common.StatusInfoType

data class ClearDisplayMessageResp(
    val status: ClearMessageStatusEnumType,
    val statusInfo: StatusInfoType? = null
): Response