package fr.simatix.cs.simulator.api.model.getchargingprofiles

import fr.simatix.cs.simulator.api.model.Request
import fr.simatix.cs.simulator.api.model.common.ChargingProfileCriterionType

data class GetChargingProfilesReq(
        val requestId: Int,
        val chargingProfile: ChargingProfileCriterionType,
        val evseId: Int?=null
) : Request