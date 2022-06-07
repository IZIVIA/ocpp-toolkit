package com.izivia.ocpp.api.model.bootnotification

import com.izivia.ocpp.api.model.Request
import com.izivia.ocpp.api.model.bootnotification.enumeration.BootReasonEnumType

data class BootNotificationReq(
    val chargingStation: ChargingStationType,
    val reason: BootReasonEnumType
): Request