package fr.simatix.cs.simulator.api.model.getchargingprofiles

import fr.simatix.cs.simulator.api.model.Response
import fr.simatix.cs.simulator.api.model.common.StatusInfoType
import fr.simatix.cs.simulator.api.model.getchargingprofiles.enumeration.GetChargingProfileStatusEnumType

data class GetChargingProfilesResp (
        val status : GetChargingProfileStatusEnumType,
        val statusInfo : StatusInfoType?=null
) : Response