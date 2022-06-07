package com.izivia.ocpp.core20.model.logstatusnotification

import com.izivia.ocpp.core20.model.logstatusnotification.enumeration.UploadLogStatusEnumType

class LogStatusNotificationReq(
    val status: UploadLogStatusEnumType,
    val requestId: Int? = null
)