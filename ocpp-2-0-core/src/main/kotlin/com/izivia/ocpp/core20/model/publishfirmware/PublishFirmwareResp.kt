package com.izivia.ocpp.core20.model.publishfirmware

import com.izivia.ocpp.core20.model.common.StatusInfoType
import com.izivia.ocpp.core20.model.common.enumeration.GenericStatusEnumType

data class PublishFirmwareResp(
        val status : GenericStatusEnumType,
        val statusInfo : StatusInfoType?=null
)
