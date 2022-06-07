package com.izivia.ocpp.core20.model.publishfirmwarestatusnotification

import com.izivia.ocpp.core20.model.publishfirmwarestatusnotification.enumeration.PublishFirmwareStatusEnumType

data class PublishFirmwareStatusNotificationReq(
    val status: PublishFirmwareStatusEnumType,
    val location: List<String>? = null,
    val requestId: Int? = null
)