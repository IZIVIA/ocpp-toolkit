package com.izivia.ocpp.api.model.getchargingprofiles

import com.izivia.ocpp.api.model.Request
import com.izivia.ocpp.api.model.common.ChargingProfileCriterionType

data class GetChargingProfilesReq(
    val requestId: Int,
    val chargingProfile: ChargingProfileCriterionType,
    val evseId: Int?=null
) : Request