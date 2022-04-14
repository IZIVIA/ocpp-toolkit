package fr.simatix.cs.simulator.core20.model.notifyevchargingneeds

import fr.simatix.cs.simulator.core20.model.common.StatusInfoType
import fr.simatix.cs.simulator.core20.model.notifyevchargingneeds.enumeration.NotifyEVChargingNeedsStatusEnumType

data class NotifyEVChargingNeedsResp(
    val status: NotifyEVChargingNeedsStatusEnumType,
    val statusInfo: StatusInfoType? = null
)