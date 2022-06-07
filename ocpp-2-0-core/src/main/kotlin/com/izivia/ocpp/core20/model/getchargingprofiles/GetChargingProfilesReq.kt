package com.izivia.ocpp.core20.model.getchargingprofiles

import com.izivia.ocpp.core20.model.common.ChargingProfileCriterionType

data class GetChargingProfilesReq(
        val requestId: Int,
        val chargingProfile: ChargingProfileCriterionType,
        val evseId: Int?=null
)