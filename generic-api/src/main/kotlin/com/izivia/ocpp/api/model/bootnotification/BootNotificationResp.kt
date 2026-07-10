package com.izivia.ocpp.api.model.bootnotification

import com.izivia.ocpp.api.model.Response
import com.izivia.ocpp.api.model.bootnotification.enumeration.RegistrationStatusEnumType
import com.izivia.ocpp.api.model.common.StatusInfoType
import kotlin.time.Instant

data class BootNotificationResp(
    val currentTime: Instant,
    val interval: Int,
    val status: RegistrationStatusEnumType,
    val statusInfo: StatusInfoType? = null
): Response