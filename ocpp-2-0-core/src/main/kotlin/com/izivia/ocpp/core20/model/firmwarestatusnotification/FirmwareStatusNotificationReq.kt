package com.izivia.ocpp.core20.model.firmwarestatusnotification

import com.izivia.ocpp.core20.model.firmwarestatusnotification.enumeration.FirmwareStatusEnumType

data class FirmwareStatusNotificationReq(
        val status: FirmwareStatusEnumType,
        val requestId: Int? = null
)