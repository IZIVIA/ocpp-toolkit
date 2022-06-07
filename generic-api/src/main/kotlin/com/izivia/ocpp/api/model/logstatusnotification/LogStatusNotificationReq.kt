package com.izivia.ocpp.api.model.logstatusnotification

import com.izivia.ocpp.api.model.Request
import com.izivia.ocpp.api.model.logstatusnotification.enumeration.UploadLogStatusEnumType

class LogStatusNotificationReq(
    val status: UploadLogStatusEnumType,
    val requestId: Int? = null
): Request