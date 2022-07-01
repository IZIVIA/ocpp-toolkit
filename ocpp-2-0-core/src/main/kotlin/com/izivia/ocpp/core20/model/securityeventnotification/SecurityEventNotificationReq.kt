package com.izivia.ocpp.core20.model.securityeventnotification

import kotlinx.datetime.Instant

data class SecurityEventNotificationReq(
    val type: String,
    val timestamp: Instant,
    val techInfo: String? = null
)