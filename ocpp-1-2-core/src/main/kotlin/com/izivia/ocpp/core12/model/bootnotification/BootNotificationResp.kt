package com.izivia.ocpp.core12.model.bootnotification

import com.izivia.ocpp.core12.model.Response
import com.izivia.ocpp.core12.model.bootnotification.enumeration.RegistrationStatus
import kotlinx.datetime.Instant

data class BootNotificationResp(
    val currentTime: Instant? = null,
    val heartbeatInterval: Int? = null,
    val status: RegistrationStatus
): Response
