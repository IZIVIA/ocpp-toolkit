package fr.simatix.cs.simulator.core20.model.unlockconnector

import fr.simatix.cs.simulator.core20.model.common.StatusInfoType
import fr.simatix.cs.simulator.core20.model.unlockconnector.enumeration.UnlockStatusEnumType

data class UnlockConnectorResp(
    val status: UnlockStatusEnumType,
    val statusInfo: StatusInfoType? = null
)