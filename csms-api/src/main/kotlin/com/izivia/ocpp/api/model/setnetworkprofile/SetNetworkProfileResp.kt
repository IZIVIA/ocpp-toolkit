package com.izivia.ocpp.api.model.setnetworkprofile

import com.izivia.ocpp.api.model.Response
import com.izivia.ocpp.api.model.common.StatusInfoType
import com.izivia.ocpp.api.model.setnetworkprofile.enumeration.SetNetworkProfileStatusEnumType


data class SetNetworkProfileResp(
    val status : SetNetworkProfileStatusEnumType,
    val statusInfo : StatusInfoType?=null
) : Response
