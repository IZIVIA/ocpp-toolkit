package com.izivia.ocpp.core16.model.statusnotification

import com.fasterxml.jackson.databind.annotation.JsonDeserialize
import com.fasterxml.jackson.databind.annotation.JsonSerialize
import com.izivia.ocpp.core16.model.statusnotification.enumeration.ChargePointStatus
import com.izivia.ocpp.core16.model.statusnotification.enumeration.ChargePointErrorCode
import com.izivia.ocpp.utils.InstantDeserializer
import com.izivia.ocpp.utils.InstantSerializer
import kotlinx.datetime.Instant

data class StatusNotificationReq(
    val connectorId: Int,
    val errorCode: ChargePointErrorCode,
    val status: ChargePointStatus,
    val info: String? = null,
    @JsonSerialize(using = InstantSerializer::class)
    @JsonDeserialize(using = InstantDeserializer::class)
    val timestamp: Instant? = null,
    val vendorErrorCode: String? = null,
    val vendorId: String? = null
)