package fr.simatix.cs.simulator.api.model.unlockconnector

data class UnlockConnectorReq(
    val connectorId: Int,
    val evseId: Int
)