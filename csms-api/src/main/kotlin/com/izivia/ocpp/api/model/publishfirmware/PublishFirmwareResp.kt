package com.izivia.ocpp.api.model.publishfirmware

import com.izivia.ocpp.api.model.Response
import com.izivia.ocpp.api.model.common.StatusInfoType
import com.izivia.ocpp.api.model.common.enumeration.GenericStatusEnumType

data class PublishFirmwareResp(
    val status : GenericStatusEnumType,
    val statusInfo : StatusInfoType?=null
) : Response
