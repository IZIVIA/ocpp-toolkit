package com.izivia.ocpp.core20.model.setnetworkprofile

import com.izivia.ocpp.core20.model.common.StatusInfoType
import com.izivia.ocpp.core20.model.setnetworkprofile.enumeration.SetNetworkProfileStatusEnumType

data class SetNetworkProfileResp(
        val status : SetNetworkProfileStatusEnumType,
        val statusInfo : StatusInfoType?=null
)
