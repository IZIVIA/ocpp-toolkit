package com.izivia.ocpp.core20.model.statusnotification

import com.izivia.ocpp.core20.model.statusnotification.enumeration.ConnectorStatusEnumType
import com.izivia.ocpp.utils.HasActionTimestamp
import kotlin.time.Instant

data class StatusNotificationReq(
    val connectorId: Int,
    val connectorStatus: ConnectorStatusEnumType,
    val evseId: Int,
    override val timestamp: Instant
) : HasActionTimestamp