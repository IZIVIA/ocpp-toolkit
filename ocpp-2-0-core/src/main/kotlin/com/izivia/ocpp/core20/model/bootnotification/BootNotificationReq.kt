package com.izivia.ocpp.core20.model.bootnotification

import com.izivia.ocpp.core20.model.bootnotification.enumeration.BootReasonEnumType

data class BootNotificationReq(
    val chargingStation: ChargingStationType,
    val reason: BootReasonEnumType
)