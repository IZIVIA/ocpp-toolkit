package fr.simatix.cs.simulator.api.model.notifyevchargingneeds

import fr.simatix.cs.simulator.api.model.Request

data class NotifyEVChargingNeedsReq(
    val evseId: Int,
    val chargingNeeds: ChargingNeedsType,
    val maxScheduleTuples: Int? = null
): Request