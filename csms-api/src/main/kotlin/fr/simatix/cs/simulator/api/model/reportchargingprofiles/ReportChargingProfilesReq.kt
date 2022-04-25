package fr.simatix.cs.simulator.api.model.reportchargingprofiles

import fr.simatix.cs.simulator.api.model.Request
import fr.simatix.cs.simulator.api.model.common.ChargingProfileType
import fr.simatix.cs.simulator.api.model.common.enumeration.ChargingLimitSourceEnumType


data class ReportChargingProfilesReq(
        val requestId : Int,
        val chargingLimitSource : ChargingLimitSourceEnumType,
        val evseId : Int,
        val chargingProfile : List<ChargingProfileType>,
        val tbc : Boolean?=false
) : Request
