package com.izivia.ocpp.core16.model.statusnotification

import com.izivia.ocpp.core16.model.Request
import com.izivia.ocpp.core16.model.statusnotification.enumeration.ChargePointErrorCode
import com.izivia.ocpp.core16.model.statusnotification.enumeration.ChargePointStatus
import com.izivia.ocpp.utils.HasActionTimestamp
import kotlin.time.Instant

data class StatusNotificationReq(
    val connectorId: Int,
    val errorCode: ChargePointErrorCode,
    val status: ChargePointStatus,
    val info: String? = null,
    override val timestamp: Instant? = null,
    val vendorErrorCode: String? = null,
    val vendorId: String? = null
) : HasActionTimestamp, Request
