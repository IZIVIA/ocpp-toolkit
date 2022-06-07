package com.izivia.ocpp.core20.model.getreport

import com.izivia.ocpp.core20.model.common.StatusInfoType
import com.izivia.ocpp.core20.model.common.enumeration.GenericDeviceModelStatusEnumType

data class GetReportResp(
    val status: GenericDeviceModelStatusEnumType,
    val statusInfo: StatusInfoType? = null
)