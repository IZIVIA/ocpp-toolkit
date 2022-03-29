package fr.simatix.cs.simulator.api.model.unlockconnector

import fr.simatix.cs.simulator.api.model.Request

data class UnlockConnectorReq(
    val connectorId: Int,
    val evseId: Int
): Request