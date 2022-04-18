package fr.simatix.cs.simulator.api.model.publishfirmwarestatusnotification

import fr.simatix.cs.simulator.api.model.Request
import fr.simatix.cs.simulator.api.model.publishfirmwarestatusnotification.enumeration.PublishFirmwareStatusEnumType

data class PublishFirmwareStatusNotificationReq(
    val status: PublishFirmwareStatusEnumType,
    val location: String? = null,
    val requestId: Int? = null
): Request