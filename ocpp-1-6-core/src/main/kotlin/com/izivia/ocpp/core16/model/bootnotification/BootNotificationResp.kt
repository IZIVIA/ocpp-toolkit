package com.izivia.ocpp.core16.model.bootnotification

import com.izivia.ocpp.core16.model.bootnotification.enumeration.RegistrationStatus
import kotlinx.datetime.Instant

data class BootNotificationResp(
    val currentTime: Instant,
    val interval: Int,
    val status: RegistrationStatus
)