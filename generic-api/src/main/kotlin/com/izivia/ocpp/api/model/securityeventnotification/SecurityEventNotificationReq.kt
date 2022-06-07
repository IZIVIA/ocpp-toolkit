package com.izivia.ocpp.api.model.securityeventnotification

import com.izivia.ocpp.api.model.Request
import kotlinx.datetime.Instant

data class SecurityEventNotificationReq(
    val type: String,
    val timestamp: Instant,
    val techInfo: String? = null
) : Request