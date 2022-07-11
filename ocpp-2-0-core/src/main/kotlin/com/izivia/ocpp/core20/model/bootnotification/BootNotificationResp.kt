package com.izivia.ocpp.core20.model.bootnotification

import com.izivia.ocpp.core20.model.bootnotification.enumeration.RegistrationStatusEnumType
import com.izivia.ocpp.core20.model.common.StatusInfoType
import kotlinx.datetime.Instant

data class BootNotificationResp(
    val currentTime: Instant,
    val interval: Int,
    val status: RegistrationStatusEnumType,
    val statusInfo: StatusInfoType? = null
)