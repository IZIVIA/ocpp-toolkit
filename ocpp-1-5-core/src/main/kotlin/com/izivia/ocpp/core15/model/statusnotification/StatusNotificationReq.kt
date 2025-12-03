package com.izivia.ocpp.core15.model.statusnotification

import com.izivia.ocpp.core15.model.Request
import com.izivia.ocpp.core15.model.statusnotification.enumeration.ChargePointErrorCode
import com.izivia.ocpp.core15.model.statusnotification.enumeration.ChargePointStatus
import com.izivia.ocpp.utils.HasActionTimestamp
import kotlinx.datetime.Instant

data class StatusNotificationReq(
    val connectorId: Int,
    val errorCode: ChargePointErrorCode,
    val info: String? = null,
    val status: ChargePointStatus,
    override val timestamp: Instant? = null,
    val vendorId: String? = null,
    val vendorErrorCode: String? = null
) : HasActionTimestamp, Request
