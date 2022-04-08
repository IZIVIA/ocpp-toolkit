package fr.simatix.cs.simulator.api.model.firmwarestatusnotification

import fr.simatix.cs.simulator.api.model.Request
import fr.simatix.cs.simulator.api.model.firmwarestatusnotification.enumeration.FirmwareStatusEnumType

data class FirmwareStatusNotificationReq(
    val status: FirmwareStatusEnumType,
    val requestId: Int? = null
): Request