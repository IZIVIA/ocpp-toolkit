package fr.simatix.cs.simulator.api.model.triggermessage

import fr.simatix.cs.simulator.api.model.Request
import fr.simatix.cs.simulator.api.model.common.EVSEType
import fr.simatix.cs.simulator.api.model.triggermessage.enumeration.MessageTriggerEnumType


data class TriggerMessageReq(
    val requestedMessage: MessageTriggerEnumType,
    val evse: EVSEType? = null
): Request