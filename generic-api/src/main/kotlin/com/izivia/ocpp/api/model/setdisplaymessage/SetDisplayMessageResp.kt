package com.izivia.ocpp.api.model.setdisplaymessage

import com.izivia.ocpp.api.model.Response
import com.izivia.ocpp.api.model.common.StatusInfoType
import com.izivia.ocpp.api.model.setdisplaymessage.enumeration.DisplayMessageStatusEnumType


data class SetDisplayMessageResp(
    val status : DisplayMessageStatusEnumType,
    val statusInfo : StatusInfoType?=null
) : Response
