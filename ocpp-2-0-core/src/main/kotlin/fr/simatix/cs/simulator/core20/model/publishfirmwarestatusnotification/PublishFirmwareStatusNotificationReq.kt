package fr.simatix.cs.simulator.core20.model.publishfirmwarestatusnotification

import fr.simatix.cs.simulator.core20.model.publishfirmwarestatusnotification.enumeration.PublishFirmwareStatusEnumType

data class PublishFirmwareStatusNotificationReq(
    val status: PublishFirmwareStatusEnumType,
    val location: String? = null,
    val requestId: Int? = null
)