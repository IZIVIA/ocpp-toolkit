package com.izivia.ocpp.core16.model.setchargingprofile

import com.izivia.ocpp.core16.model.setchargingprofile.enumeration.ChargingProfileStatus

data class SetChargingProfileResp(
    val status: ChargingProfileStatus
)
