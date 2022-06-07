package com.izivia.ocpp.api.model.firmwarestatusnotification

import com.izivia.ocpp.api.model.Request
import com.izivia.ocpp.api.model.firmwarestatusnotification.enumeration.FirmwareStatusEnumType

data class FirmwareStatusNotificationReq(
    val status: FirmwareStatusEnumType,
    val requestId: Int? = null
): Request