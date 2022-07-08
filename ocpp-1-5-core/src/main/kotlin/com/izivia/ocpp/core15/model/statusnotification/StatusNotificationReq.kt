package com.izivia.ocpp.core15.model.statusnotification

import com.izivia.ocpp.core15.model.statusnotification.enumeration.ChargePointErrorCode
import com.izivia.ocpp.core15.model.statusnotification.enumeration.ChargePointStatus
import kotlinx.datetime.Instant

data class StatusNotificationReq(
    val connectorId: Int,
    val errorCode: ChargePointErrorCode,
    val info: String? = null,
    val status: ChargePointStatus,
    val timestamp: Instant? = null,
    val vendorId: String? = null,
    val vendorErrorCode: String? = null
)
