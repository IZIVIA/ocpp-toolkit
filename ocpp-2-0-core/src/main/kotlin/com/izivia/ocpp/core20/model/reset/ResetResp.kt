package com.izivia.ocpp.core20.model.reset

import com.izivia.ocpp.core20.model.common.StatusInfoType
import com.izivia.ocpp.core20.model.reset.enumeration.ResetStatusEnumType

data class ResetResp(
    val status: ResetStatusEnumType,
    val statusInfo: StatusInfoType? = null
)