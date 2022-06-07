package com.izivia.ocpp.api.model.clearchargingprofile

import com.izivia.ocpp.api.model.Response
import com.izivia.ocpp.api.model.clearchargingprofile.enumeration.ClearChargingProfileStatusEnumType
import com.izivia.ocpp.api.model.common.StatusInfoType

data class ClearChargingProfileResp(
    val status: ClearChargingProfileStatusEnumType,
    val statusInfo: StatusInfoType? = null
): Response
