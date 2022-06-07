package com.izivia.ocpp.core16.model.setchargingprofile

import com.izivia.ocpp.core16.model.common.ChargingProfile

data class SetChargingProfileReq(
    val connectorId: Int,
    val csChargingProfiles: ChargingProfile
)