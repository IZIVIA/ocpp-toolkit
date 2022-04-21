package fr.simatix.cs.simulator.core20.model.getchargingprofiles

import fr.simatix.cs.simulator.core20.model.common.StatusInfoType
import fr.simatix.cs.simulator.core20.model.getchargingprofiles.enumeration.GetChargingProfileStatusEnumType

data class GetChargingProfilesResp (
        val status : GetChargingProfileStatusEnumType,
        val statusInfo : StatusInfoType?=null
)