package com.izivia.ocpp.core20.model.getbasereport

import com.izivia.ocpp.core20.model.common.StatusInfoType
import com.izivia.ocpp.core20.model.common.enumeration.GenericDeviceModelStatusEnumType

data class GetBaseReportResp(
    val status: GenericDeviceModelStatusEnumType,
    val statusInfo: StatusInfoType? = null
)