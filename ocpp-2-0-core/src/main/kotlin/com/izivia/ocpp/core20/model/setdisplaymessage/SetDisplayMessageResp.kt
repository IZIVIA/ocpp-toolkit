package com.izivia.ocpp.core20.model.setdisplaymessage

import com.izivia.ocpp.core20.model.common.StatusInfoType
import com.izivia.ocpp.core20.model.setdisplaymessage.enumeration.DisplayMessageStatusEnumType

data class SetDisplayMessageResp(
        val status : DisplayMessageStatusEnumType,
        val statusInfo : StatusInfoType?=null
)
