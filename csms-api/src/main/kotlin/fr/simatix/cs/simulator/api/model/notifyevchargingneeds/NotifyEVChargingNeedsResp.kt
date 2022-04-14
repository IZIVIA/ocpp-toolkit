package fr.simatix.cs.simulator.api.model.notifyevchargingneeds

import fr.simatix.cs.simulator.api.model.Response
import fr.simatix.cs.simulator.api.model.common.StatusInfoType
import fr.simatix.cs.simulator.api.model.notifyevchargingneeds.enumeration.NotifyEVChargingNeedsStatusEnumType

data class NotifyEVChargingNeedsResp(
    val status: NotifyEVChargingNeedsStatusEnumType,
    val statusInfo: StatusInfoType? = null
): Response