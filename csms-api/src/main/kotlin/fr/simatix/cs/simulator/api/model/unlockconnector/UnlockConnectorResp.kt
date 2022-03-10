package fr.simatix.cs.simulator.api.model.unlockconnector

import fr.simatix.cs.simulator.api.model.common.StatusInfoType
import fr.simatix.cs.simulator.api.model.unlockconnector.enumeration.UnlockStatusEnumType

data class UnlockConnectorResp(
    val status: UnlockStatusEnumType,
    val statusInfo: StatusInfoType? = null
)