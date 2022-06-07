package com.izivia.ocpp.core20.model.remotestart

import com.izivia.ocpp.core20.model.common.StatusInfoType
import com.izivia.ocpp.core20.model.common.enumeration.RequestStartStopStatusEnumType

data class RequestStartTransactionResp(
    val status: RequestStartStopStatusEnumType,
    val transactionId: String? = null,
    val statusInfo: StatusInfoType? = null
)