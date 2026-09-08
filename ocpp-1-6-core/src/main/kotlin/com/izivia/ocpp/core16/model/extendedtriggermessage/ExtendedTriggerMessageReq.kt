package com.izivia.ocpp.core16.model.extendedtriggermessage

import com.izivia.ocpp.core16.model.Request
import com.izivia.ocpp.core16.model.extendedtriggermessage.enumeration.ExtendedMessageTriggerEnumType

data class ExtendedTriggerMessageReq(
    val requestedMessage: ExtendedMessageTriggerEnumType,
    val connectorId: Int? = null
) : Request
