package com.izivia.ocpp.api.model.getchargingprofiles

import com.izivia.ocpp.api.model.Response
import com.izivia.ocpp.api.model.common.StatusInfoType
import com.izivia.ocpp.api.model.getchargingprofiles.enumeration.GetChargingProfileStatusEnumType

data class GetChargingProfilesResp (
    val status : GetChargingProfileStatusEnumType,
    val statusInfo : StatusInfoType?=null
) : Response