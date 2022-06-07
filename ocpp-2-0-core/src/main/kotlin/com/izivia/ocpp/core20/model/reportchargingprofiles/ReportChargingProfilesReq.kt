package com.izivia.ocpp.core20.model.reportchargingprofiles

import com.izivia.ocpp.core20.model.common.ChargingProfileType
import com.izivia.ocpp.core20.model.common.enumeration.ChargingLimitSourceEnumType

data class ReportChargingProfilesReq(
    val requestId : Int,
    val chargingLimitSource : ChargingLimitSourceEnumType,
    val evseId : Int,
    val chargingProfile : List<ChargingProfileType>,
    val tbc : Boolean?=false
)
