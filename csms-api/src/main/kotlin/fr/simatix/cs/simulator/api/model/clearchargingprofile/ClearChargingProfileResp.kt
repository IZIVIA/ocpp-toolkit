package fr.simatix.cs.simulator.api.model.clearchargingprofile

import fr.simatix.cs.simulator.api.model.Response
import fr.simatix.cs.simulator.api.model.clearchargingprofile.enumeration.ClearChargingProfileStatusEnumType
import fr.simatix.cs.simulator.api.model.common.StatusInfoType

data class ClearChargingProfileResp(
    val status: ClearChargingProfileStatusEnumType,
    val statusInfo: StatusInfoType? = null
): Response
