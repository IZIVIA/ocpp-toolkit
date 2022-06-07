package com.izivia.ocpp.api.model.getlog

import com.izivia.ocpp.api.model.Request
import com.izivia.ocpp.api.model.getlog.enumeration.LogEnumType

data class GetLogReq(
    val requestId: Int,
    val logType: LogEnumType,
    val log: LogParametersType,
    val retries: Int? = null,
    val retryInterval: Int? = null
): Request