package fr.simatix.cs.simulator.core20.model.firmwarestatusnotification

import fr.simatix.cs.simulator.core20.model.firmwarestatusnotification.enumeration.FirmwareStatusEnumType

data class FirmwareStatusNotificationReq(
        val status: FirmwareStatusEnumType,
        val requestId: Int? = null
)