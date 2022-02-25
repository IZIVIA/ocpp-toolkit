package fr.simatix.cs.simulator.core20.model.bootnotification

import fr.simatix.cs.simulator.core20.model.bootnotification.enumeration.BootReasonEnumType

data class BootNotificationReq(
    val chargingStation: ChargingStationType,
    val reason: BootReasonEnumType
)