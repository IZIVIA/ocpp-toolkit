package com.izivia.ocpp.core16.model.getlog

import com.izivia.ocpp.core16.model.Request
import com.izivia.ocpp.core16.model.getlog.enumeration.LogEnumType
import kotlin.time.Instant

data class GetLogReq(
    val logType: LogEnumType,
    val requestId: Int,
    val retries: Int?,
    val retryInterval: Int?,
    val log: LogParametersType
) : Request

data class LogParametersType(
    val remoteLocation: String,
    val oldestTimestamp: Instant?,
    val latestTimestamp: Instant?
)
