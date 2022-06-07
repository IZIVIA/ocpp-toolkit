package com.izivia.ocpp.api.model.setchargingprofile

import com.izivia.ocpp.api.model.Response
import com.izivia.ocpp.api.model.common.StatusInfoType
import com.izivia.ocpp.api.model.setchargingprofile.enumeration.ChargingProfileStatusEnumType

data class SetChargingProfileResp(
    val status: ChargingProfileStatusEnumType,
    val statusInfo: StatusInfoType? = null
): Response