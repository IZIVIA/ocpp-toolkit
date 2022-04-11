package fr.simatix.cs.simulator.core20.model.triggermessage

import fr.simatix.cs.simulator.core20.model.common.StatusInfoType
import fr.simatix.cs.simulator.core20.model.triggermessage.enumeration.TriggerMessageStatusEnumType

data class TriggerMessageResp(
    val status: TriggerMessageStatusEnumType,
    val statusInfo: StatusInfoType? = null
)