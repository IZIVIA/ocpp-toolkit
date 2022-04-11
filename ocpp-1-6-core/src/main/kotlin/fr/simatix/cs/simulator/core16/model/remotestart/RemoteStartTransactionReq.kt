package fr.simatix.cs.simulator.core16.model.remotestart

import fr.simatix.cs.simulator.core16.model.common.ChargingProfile

data class RemoteStartTransactionReq(
    val idTag: String,
    val connectorId: Int? = null,
    val chargingProfile: ChargingProfile? = null
)