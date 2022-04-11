package fr.simatix.cs.simulator.core16.model.triggermessage

import fr.simatix.cs.simulator.core16.model.triggermessage.enumeration.MessageTrigger

data class TriggerMessageReq(
    val requestedMessage: MessageTrigger,
    val connectorId: Int? = null
)