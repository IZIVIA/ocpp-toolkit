package com.izivia.ocpp.api.model.remotestop

import com.izivia.ocpp.api.model.Response
import com.izivia.ocpp.api.model.common.StatusInfoType
import com.izivia.ocpp.api.model.common.enumeration.RequestStartStopStatusEnumType

data class RequestStopTransactionResp(
    val status: RequestStartStopStatusEnumType,
    val statusInfo: StatusInfoType? = null
): Response