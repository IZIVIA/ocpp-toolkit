package com.izivia.ocpp.core20.model.remotestop

import com.izivia.ocpp.core20.model.common.StatusInfoType
import com.izivia.ocpp.core20.model.common.enumeration.RequestStartStopStatusEnumType

data class RequestStopTransactionResp(
    val status: RequestStartStopStatusEnumType,
    val statusInfo: StatusInfoType? = null
)