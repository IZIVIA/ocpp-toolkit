package com.izivia.ocpp.api.model.triggermessage

import com.izivia.ocpp.api.model.Response
import com.izivia.ocpp.api.model.common.StatusInfoType
import com.izivia.ocpp.api.model.triggermessage.enumeration.TriggerMessageStatusEnumType

data class TriggerMessageResp(
    val status: TriggerMessageStatusEnumType,
    val statusInfo: StatusInfoType? = null
): Response