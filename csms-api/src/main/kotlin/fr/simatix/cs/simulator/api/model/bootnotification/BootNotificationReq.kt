package fr.simatix.cs.simulator.api.model.bootnotification

import fr.simatix.cs.simulator.api.model.Request
import fr.simatix.cs.simulator.api.model.bootnotification.enumeration.BootReasonEnumType

data class BootNotificationReq(
    val chargingStation: ChargingStationType,
    val reason: BootReasonEnumType
): Request