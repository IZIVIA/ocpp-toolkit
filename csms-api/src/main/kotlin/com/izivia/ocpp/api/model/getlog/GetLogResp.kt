package com.izivia.ocpp.api.model.getlog

import com.izivia.ocpp.api.model.Response
import com.izivia.ocpp.api.model.common.StatusInfoType
import com.izivia.ocpp.api.model.getlog.enumeration.LogStatusEnumType

data class GetLogResp(
    val status: LogStatusEnumType,
    val filename: String? = null,
    val statusInfo: StatusInfoType? = null
): Response