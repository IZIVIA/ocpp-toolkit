package com.izivia.ocpp.api.model.customerinformation

import com.izivia.ocpp.api.model.Response
import com.izivia.ocpp.api.model.common.StatusInfoType
import com.izivia.ocpp.api.model.customerinformation.enumeration.CustomerInformationStatusEnumType

data class CustomerInformationResp(
    val status : CustomerInformationStatusEnumType,
    val statusInfo : StatusInfoType?=null
) : Response
