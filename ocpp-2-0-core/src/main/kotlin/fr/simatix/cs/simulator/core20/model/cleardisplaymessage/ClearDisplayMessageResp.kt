package fr.simatix.cs.simulator.core20.model.cleardisplaymessage

import fr.simatix.cs.simulator.core20.model.cleardisplaymessage.enumeration.ClearMessageStatusEnumType
import fr.simatix.cs.simulator.core20.model.common.StatusInfoType

data class ClearDisplayMessageResp(
    val status: ClearMessageStatusEnumType,
    val statusInfo: StatusInfoType? = null
)