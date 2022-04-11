package fr.simatix.cs.simulator.core20.model.triggermessage

import fr.simatix.cs.simulator.core20.model.common.EVSEType
import fr.simatix.cs.simulator.core20.model.triggermessage.enumeration.MessageTriggerEnumType


data class TriggerMessageReq(
    val requestedMessage: MessageTriggerEnumType,
    val evse: EVSEType? = null
)