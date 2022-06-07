package com.izivia.ocpp.core16.model.triggermessage

import com.izivia.ocpp.core16.model.triggermessage.enumeration.MessageTrigger

data class TriggerMessageReq(
    val requestedMessage: MessageTrigger,
    val connectorId: Int? = null
)