package com.izivia.ocpp.api.model.reportchargingprofiles

import com.izivia.ocpp.api.model.Request
import com.izivia.ocpp.api.model.common.ChargingProfileType
import com.izivia.ocpp.api.model.common.enumeration.ChargingLimitSourceEnumType


data class ReportChargingProfilesReq(
    val requestId : Int,
    val chargingLimitSource : ChargingLimitSourceEnumType,
    val evseId : Int,
    val chargingProfile : List<ChargingProfileType>,
    val tbc : Boolean?=false
) : Request
