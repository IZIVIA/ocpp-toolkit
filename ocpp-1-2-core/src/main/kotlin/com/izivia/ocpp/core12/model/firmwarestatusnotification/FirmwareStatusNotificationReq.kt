package com.izivia.ocpp.core12.model.firmwarestatusnotification

import com.izivia.ocpp.core12.model.Request
import com.izivia.ocpp.core12.model.firmwarestatusnotification.enumeration.FirmwareStatus

data class FirmwareStatusNotificationReq(
        val status: FirmwareStatus
): Request
