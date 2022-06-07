package com.izivia.ocpp.core16.model.clearchargingprofile

import com.izivia.ocpp.core16.model.clearchargingprofile.enumeration.ClearChargingProfileStatus

data class ClearChargingProfileResp(
    val status: ClearChargingProfileStatus
)