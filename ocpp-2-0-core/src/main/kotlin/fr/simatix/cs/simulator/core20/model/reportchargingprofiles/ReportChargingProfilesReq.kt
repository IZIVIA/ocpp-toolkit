package fr.simatix.cs.simulator.core20.model.reportchargingprofiles

import fr.simatix.cs.simulator.core20.model.common.ChargingProfileType
import fr.simatix.cs.simulator.core20.model.common.enumeration.ChargingLimitSourceEnumType

data class ReportChargingProfilesReq(
    val requestId : Int,
    val chargingLimitSource : ChargingLimitSourceEnumType,
    val evseId : Int,
    val chargingProfile : List<ChargingProfileType>,
    val tbc : Boolean?=false
)
