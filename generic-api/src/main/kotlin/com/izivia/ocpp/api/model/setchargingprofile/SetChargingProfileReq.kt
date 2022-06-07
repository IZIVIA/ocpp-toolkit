package com.izivia.ocpp.api.model.setchargingprofile

import com.izivia.ocpp.api.model.Request
import com.izivia.ocpp.api.model.common.ChargingProfileType

data class SetChargingProfileReq(
    val evseId: Int,
    val chargingProfile: ChargingProfileType
): Request