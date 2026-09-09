package com.izivia.ocpp.core12.model.statusnotification

import com.izivia.ocpp.core12.model.Request
import com.izivia.ocpp.core12.model.statusnotification.enumeration.ChargePointErrorCode
import com.izivia.ocpp.core12.model.statusnotification.enumeration.ChargePointStatus

data class StatusNotificationReq(
    val connectorId: Int,
    val status: ChargePointStatus,
    val errorCode: ChargePointErrorCode
) : Request
