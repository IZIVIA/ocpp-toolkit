package fr.simatix.cs.simulator.core20.model.notifyevchargingneeds

data class NotifyEVChargingNeedsReq(
    val evseId: Int,
    val chargingNeeds: ChargingNeedsType,
    val maxScheduleTuples: Int? = null
)