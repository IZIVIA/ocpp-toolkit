package fr.simatix.cs.simulator.core16.model.triggermessage

import fr.simatix.cs.simulator.core16.model.triggermessage.enumeration.TriggerMessageStatus

data class TriggerMessageResp(
    val status: TriggerMessageStatus
)