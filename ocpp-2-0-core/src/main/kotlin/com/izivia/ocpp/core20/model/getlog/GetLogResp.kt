package com.izivia.ocpp.core20.model.getlog

import com.izivia.ocpp.core20.model.common.StatusInfoType
import com.izivia.ocpp.core20.model.getlog.enumeration.LogStatusEnumType

data class GetLogResp(
    val status: LogStatusEnumType,
    val filename: String? = null,
    val statusInfo: StatusInfoType? = null
)