package com.izivia.ocpp.core20.model.sendlocallist

import com.izivia.ocpp.core20.model.common.StatusInfoType
import com.izivia.ocpp.core20.model.sendlocallist.enumeration.SendLocalListStatusEnumType

data class SendLocalListResp(
    val status: SendLocalListStatusEnumType,
    val statusInfo: StatusInfoType? = null
)