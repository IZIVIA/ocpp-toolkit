package com.izivia.ocpp.core20.model.setmonitoringbase

import com.izivia.ocpp.core20.model.common.StatusInfoType
import com.izivia.ocpp.core20.model.common.enumeration.GenericDeviceModelStatusEnumType

data class SetMonitoringBaseResp(
        val status : GenericDeviceModelStatusEnumType,
        val statusInfo : StatusInfoType?=null
)
