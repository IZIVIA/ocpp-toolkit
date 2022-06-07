package com.izivia.ocpp.core20.model.triggermessage

import com.izivia.ocpp.core20.model.common.StatusInfoType
import com.izivia.ocpp.core20.model.triggermessage.enumeration.TriggerMessageStatusEnumType

data class TriggerMessageResp(
    val status: TriggerMessageStatusEnumType,
    val statusInfo: StatusInfoType? = null
)