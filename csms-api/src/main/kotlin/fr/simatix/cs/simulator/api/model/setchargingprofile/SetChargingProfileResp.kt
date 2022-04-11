package fr.simatix.cs.simulator.api.model.setchargingprofile

import fr.simatix.cs.simulator.api.model.Response
import fr.simatix.cs.simulator.api.model.common.StatusInfoType
import fr.simatix.cs.simulator.api.model.setchargingprofile.enumeration.ChargingProfileStatusEnumType

data class SetChargingProfileResp(
    val status: ChargingProfileStatusEnumType,
    val statusInfo: StatusInfoType? = null
): Response