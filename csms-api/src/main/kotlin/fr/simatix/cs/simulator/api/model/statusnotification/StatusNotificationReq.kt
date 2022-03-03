package fr.simatix.cs.simulator.api.model.statusnotification

import fr.simatix.cs.simulator.api.model.statusnotification.enumeration.ChargePointErrorCode
import fr.simatix.cs.simulator.api.model.statusnotification.enumeration.ConnectorStatusEnumType
import kotlinx.datetime.Instant

data class StatusNotificationReq(
    val connectorId: Int,
    val connectorStatus: ConnectorStatusEnumType,
    val evseId: Int,
    val timestamp: Instant,
    val errorCode: ChargePointErrorCode = ChargePointErrorCode.NoError,
)