package com.izivia.ocpp.core20.model.setmonitoringlevel

import com.izivia.ocpp.core20.model.common.StatusInfoType
import com.izivia.ocpp.core20.model.common.enumeration.GenericStatusEnumType

data class SetMonitoringLevelResp(
        val status : GenericStatusEnumType,
        val statusInfo : StatusInfoType?=null
)
