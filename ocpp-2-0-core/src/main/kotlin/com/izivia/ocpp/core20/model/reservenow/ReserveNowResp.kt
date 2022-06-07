package com.izivia.ocpp.core20.model.reservenow

import com.izivia.ocpp.core20.model.common.StatusInfoType
import com.izivia.ocpp.core20.model.reservenow.enumeration.ReserveNowStatusEnumType

data class ReserveNowResp(
    val status: ReserveNowStatusEnumType,
    val statusInfo: StatusInfoType? =null
)