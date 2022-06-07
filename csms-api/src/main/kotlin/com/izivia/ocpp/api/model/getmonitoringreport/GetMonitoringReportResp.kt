package com.izivia.ocpp.api.model.getmonitoringreport

import com.izivia.ocpp.api.model.Response
import com.izivia.ocpp.api.model.common.StatusInfoType
import com.izivia.ocpp.api.model.common.enumeration.GenericDeviceModelStatusEnumType

data class GetMonitoringReportResp(
    val status : GenericDeviceModelStatusEnumType,
    val statusInfo : StatusInfoType?=null
) : Response
