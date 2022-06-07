package com.izivia.ocpp.api.model.setmonitoringbase

import com.izivia.ocpp.api.model.Response
import com.izivia.ocpp.api.model.common.StatusInfoType
import com.izivia.ocpp.api.model.common.enumeration.GenericDeviceModelStatusEnumType

data class SetMonitoringBaseResp(
    val status : GenericDeviceModelStatusEnumType,
    val statusInfo : StatusInfoType?=null
) : Response
