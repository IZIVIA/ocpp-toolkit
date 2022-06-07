package com.izivia.ocpp.core20.model.setchargingprofile

import com.izivia.ocpp.core20.model.common.ChargingProfileType

data class SetChargingProfileReq(
    val evseId: Int,
    val chargingProfile: ChargingProfileType
)