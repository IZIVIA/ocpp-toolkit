package com.izivia.ocpp.api.model.publishfirmwarestatusnotification

import com.izivia.ocpp.api.model.Request
import com.izivia.ocpp.api.model.publishfirmwarestatusnotification.enumeration.PublishFirmwareStatusEnumType

data class PublishFirmwareStatusNotificationReq(
    val status: PublishFirmwareStatusEnumType,
    val location: List<String>? = null,
    val requestId: Int? = null
): Request