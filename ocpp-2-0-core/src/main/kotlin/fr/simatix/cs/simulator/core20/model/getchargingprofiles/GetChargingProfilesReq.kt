package fr.simatix.cs.simulator.core20.model.getchargingprofiles

import fr.simatix.cs.simulator.core20.model.common.ChargingProfileCriterionType

data class GetChargingProfilesReq(
        val requestId: Int,
        val chargingProfile: ChargingProfileCriterionType,
        val evseId: Int?=null
)