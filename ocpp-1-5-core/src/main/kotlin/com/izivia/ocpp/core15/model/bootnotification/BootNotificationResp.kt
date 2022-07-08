package com.izivia.ocpp.core15.model.bootnotification

import com.izivia.ocpp.core15.model.bootnotification.enumeration.RegistrationStatus
import kotlinx.datetime.Instant

data class BootNotificationResp(
    val currentTime: Instant,
    val heartbeatInterval: Int,
    val status: RegistrationStatus
)
