package fr.simatix.cs.simulator.core16.model.unlockconnector

import fr.simatix.cs.simulator.core16.model.unlockconnector.enumeration.UnlockStatus

data class UnlockConnectorResp(
    val status: UnlockStatus
)