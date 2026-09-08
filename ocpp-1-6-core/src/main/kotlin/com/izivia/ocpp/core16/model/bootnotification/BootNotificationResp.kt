package com.izivia.ocpp.core16.model.bootnotification

import com.izivia.ocpp.core16.model.Response
import com.izivia.ocpp.core16.model.bootnotification.enumeration.RegistrationStatus
import kotlin.time.Instant

data class BootNotificationResp(
    val currentTime: Instant,
    val interval: Int,
    val status: RegistrationStatus
) : Response
