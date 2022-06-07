package com.izivia.ocpp.core20.model.triggermessage

import com.izivia.ocpp.core20.model.common.EVSEType
import com.izivia.ocpp.core20.model.triggermessage.enumeration.MessageTriggerEnumType


data class TriggerMessageReq(
    val requestedMessage: MessageTriggerEnumType,
    val evse: EVSEType? = null
)