package fr.simatix.cs.simulator.core20.model.clearchargingprofile

import fr.simatix.cs.simulator.core20.model.clearchargingprofile.enumeration.ClearChargingProfileEnumType
import fr.simatix.cs.simulator.core20.model.common.StatusInfoType

data class ClearChargingProfileResp(
    val status: ClearChargingProfileEnumType,
    val statusInfo: StatusInfoType? = null
)
