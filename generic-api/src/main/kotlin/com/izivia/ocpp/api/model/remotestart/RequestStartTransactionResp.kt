package com.izivia.ocpp.api.model.remotestart

import com.izivia.ocpp.api.model.Response
import com.izivia.ocpp.api.model.common.StatusInfoType
import com.izivia.ocpp.api.model.common.enumeration.RequestStartStopStatusEnumType

data class RequestStartTransactionResp(
    val status: RequestStartStopStatusEnumType,
    val transactionId: String? = null,
    val statusInfo: StatusInfoType? = null
): Response