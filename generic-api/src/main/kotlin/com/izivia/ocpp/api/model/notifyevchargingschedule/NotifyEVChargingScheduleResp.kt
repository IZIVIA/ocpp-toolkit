package com.izivia.ocpp.api.model.notifyevchargingschedule

import com.izivia.ocpp.api.model.Response
import com.izivia.ocpp.api.model.common.StatusInfoType
import com.izivia.ocpp.api.model.common.enumeration.GenericStatusEnumType

data class NotifyEVChargingScheduleResp(
    val status: GenericStatusEnumType,
    val statusInfo: StatusInfoType
): Response