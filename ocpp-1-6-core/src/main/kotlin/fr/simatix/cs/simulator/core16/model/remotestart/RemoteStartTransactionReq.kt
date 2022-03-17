package fr.simatix.cs.simulator.core16.model.remotestart

data class RemoteStartTransactionReq(
    val idTag: String,
    val connectorId: Int? = null,
    val chargingProfile: ChargingProfile? = null
)