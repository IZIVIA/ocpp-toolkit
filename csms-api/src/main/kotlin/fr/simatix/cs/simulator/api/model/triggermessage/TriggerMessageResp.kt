package fr.simatix.cs.simulator.api.model.triggermessage

import fr.simatix.cs.simulator.api.model.Response
import fr.simatix.cs.simulator.api.model.common.StatusInfoType
import fr.simatix.cs.simulator.api.model.triggermessage.enumeration.TriggerMessageStatusEnumType

data class TriggerMessageResp(
    val status: TriggerMessageStatusEnumType,
    val statusInfo: StatusInfoType? = null
): Response