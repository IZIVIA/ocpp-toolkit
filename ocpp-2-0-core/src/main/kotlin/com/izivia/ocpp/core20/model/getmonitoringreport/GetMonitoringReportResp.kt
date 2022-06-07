package com.izivia.ocpp.core20.model.getmonitoringreport

import com.izivia.ocpp.core20.model.common.StatusInfoType
import com.izivia.ocpp.core20.model.common.enumeration.GenericDeviceModelStatusEnumType

data class GetMonitoringReportResp(
    val status : GenericDeviceModelStatusEnumType,
    val statusInfo : StatusInfoType?=null
)
