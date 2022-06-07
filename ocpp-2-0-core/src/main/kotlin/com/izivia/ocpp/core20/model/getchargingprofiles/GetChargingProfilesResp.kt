package com.izivia.ocpp.core20.model.getchargingprofiles

import com.izivia.ocpp.core20.model.common.StatusInfoType
import com.izivia.ocpp.core20.model.getchargingprofiles.enumeration.GetChargingProfileStatusEnumType

data class GetChargingProfilesResp (
        val status : GetChargingProfileStatusEnumType,
        val statusInfo : StatusInfoType?=null
)