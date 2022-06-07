package com.izivia.ocpp.core20.model.getdisplaymessages

import com.izivia.ocpp.core20.model.common.StatusInfoType
import com.izivia.ocpp.core20.model.getdisplaymessages.enumeration.GetDisplayMessagesStatusEnumType

data class GetDisplayMessagesResp (
        val status: GetDisplayMessagesStatusEnumType,
        val statusInfo: StatusInfoType?=null
)