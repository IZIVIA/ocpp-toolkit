package com.izivia.ocpp.core16.model.triggermessage

import com.izivia.ocpp.core16.model.triggermessage.enumeration.TriggerMessageStatus

data class TriggerMessageResp(
    val status: TriggerMessageStatus
)