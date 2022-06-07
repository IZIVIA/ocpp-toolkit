package com.izivia.ocpp.api.model.reservenow

import com.izivia.ocpp.api.model.Response
import com.izivia.ocpp.api.model.common.StatusInfoType
import com.izivia.ocpp.api.model.reservenow.enumeration.ReserveNowStatusEnumType

data class ReserveNowResp(
    val status: ReserveNowStatusEnumType,
    val statusInfo: StatusInfoType? =null
): Response