package com.izivia.ocpp.api.model.reset

import com.izivia.ocpp.api.model.Response
import com.izivia.ocpp.api.model.common.StatusInfoType
import com.izivia.ocpp.api.model.reset.enumeration.ResetStatusEnumType

data class ResetResp(
    val status: ResetStatusEnumType,
    val statusInfo: StatusInfoType? = null
): Response