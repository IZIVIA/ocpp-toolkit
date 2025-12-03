package com.izivia.ocpp.core15.model.firmwarestatusnotification

import com.izivia.ocpp.core15.model.Request
import com.izivia.ocpp.core15.model.firmwarestatusnotification.enumeration.FirmwareStatus

data class FirmwareStatusNotificationReq(
        val status: FirmwareStatus
): Request
