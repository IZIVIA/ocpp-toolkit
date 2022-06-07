package com.izivia.ocpp.api.model.getdisplaymessages

import com.izivia.ocpp.api.model.Response
import com.izivia.ocpp.api.model.common.StatusInfoType
import com.izivia.ocpp.api.model.getdisplaymessages.enumeration.GetDisplayMessagesStatusEnumType

data class GetDisplayMessagesResp (
    val status: GetDisplayMessagesStatusEnumType,
    val statusInfo: StatusInfoType?=null
) : Response