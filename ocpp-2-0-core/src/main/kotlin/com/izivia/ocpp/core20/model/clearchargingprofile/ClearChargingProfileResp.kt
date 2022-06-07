package com.izivia.ocpp.core20.model.clearchargingprofile

import com.izivia.ocpp.core20.model.clearchargingprofile.enumeration.ClearChargingProfileEnumType
import com.izivia.ocpp.core20.model.common.StatusInfoType

data class ClearChargingProfileResp(
    val status: ClearChargingProfileEnumType,
    val statusInfo: StatusInfoType? = null
)
