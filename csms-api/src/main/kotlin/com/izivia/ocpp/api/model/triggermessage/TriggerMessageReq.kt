package com.izivia.ocpp.api.model.triggermessage

import com.izivia.ocpp.api.model.Request
import com.izivia.ocpp.api.model.common.EVSEType
import com.izivia.ocpp.api.model.triggermessage.enumeration.MessageTriggerEnumType


data class TriggerMessageReq(
    val requestedMessage: MessageTriggerEnumType,
    val evse: EVSEType? = null
): Request