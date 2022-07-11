package com.izivia.ocpp.core20.model.statusnotification

import com.izivia.ocpp.core20.model.statusnotification.enumeration.ConnectorStatusEnumType
import kotlinx.datetime.Instant

data class StatusNotificationReq(
    val connectorId: Int,
    val connectorStatus: ConnectorStatusEnumType,
    val evseId: Int,
    val timestamp: Instant
)