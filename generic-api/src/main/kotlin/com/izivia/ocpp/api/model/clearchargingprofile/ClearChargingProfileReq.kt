package com.izivia.ocpp.api.model.clearchargingprofile

import com.izivia.ocpp.api.model.Request

data class ClearChargingProfileReq(
    val chargingProfileId: Int? = null,
    val chargingProfileCriteria: ClearChargingProfileType? = null
): Request
