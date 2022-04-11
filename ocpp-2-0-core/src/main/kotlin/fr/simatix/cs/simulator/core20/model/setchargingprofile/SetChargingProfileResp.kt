package fr.simatix.cs.simulator.core20.model.setchargingprofile

import fr.simatix.cs.simulator.core20.model.common.StatusInfoType
import fr.simatix.cs.simulator.core20.model.setchargingprofile.enumeration.ChargingProfileStatusEnumType

data class SetChargingProfileResp(
    val status: ChargingProfileStatusEnumType,
    val statusInfo: StatusInfoType? = null
)