package com.izivia.ocpp.api.model.statusnotification

import com.izivia.ocpp.api.model.Request
import com.izivia.ocpp.api.model.statusnotification.enumeration.ChargePointErrorCode
import com.izivia.ocpp.api.model.statusnotification.enumeration.ConnectorStatusEnumType
import kotlinx.datetime.Instant

data class StatusNotificationReq(
    val connectorId: Int,
    val connectorStatus: ConnectorStatusEnumType,
    val evseId: Int,
    val timestamp: Instant,
    val errorCode: ChargePointErrorCode = ChargePointErrorCode.NoError,
): Request