package com.izivia.ocpp.core20.model.customerinformation

import com.izivia.ocpp.core20.model.common.StatusInfoType
import com.izivia.ocpp.core20.model.customerinformation.enumeration.CustomerInformationStatusEnumType

data class CustomerInformationResp(
        val status : CustomerInformationStatusEnumType,
        val statusInfo : StatusInfoType?=null
)
