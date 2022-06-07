package com.izivia.ocpp.core20.model.setchargingprofile

import com.izivia.ocpp.core20.model.common.StatusInfoType
import com.izivia.ocpp.core20.model.setchargingprofile.enumeration.ChargingProfileStatusEnumType

data class SetChargingProfileResp(
    val status: ChargingProfileStatusEnumType,
    val statusInfo: StatusInfoType? = null
)