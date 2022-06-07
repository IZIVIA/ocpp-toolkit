package com.izivia.ocpp.core20.model.notifyevchargingschedule

import com.izivia.ocpp.core20.model.common.StatusInfoType
import com.izivia.ocpp.core20.model.common.enumeration.GenericStatusEnumType

data class NotifyEVChargingScheduleResp(
    val status: GenericStatusEnumType,
    val statusInfo: StatusInfoType
)