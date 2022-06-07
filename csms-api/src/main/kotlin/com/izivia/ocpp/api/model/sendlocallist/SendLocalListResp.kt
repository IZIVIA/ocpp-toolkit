package com.izivia.ocpp.api.model.sendlocallist

import com.izivia.ocpp.api.model.Response
import com.izivia.ocpp.api.model.common.StatusInfoType
import com.izivia.ocpp.api.model.sendlocallist.enumeration.SendLocalListStatusEnumType

data class SendLocalListResp(
    val status: SendLocalListStatusEnumType,
    val statusInfo: StatusInfoType? = null
): Response
