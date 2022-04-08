package fr.simatix.cs.simulator.core16.model.clearchargingprofile

import fr.simatix.cs.simulator.core16.model.common.enumeration.ChargingProfilePurposeType

data class ClearChargingProfileReq(
    val id: Int? = null,
    val connectorId: Int? = null,
    val chargingProfilePurpose: ChargingProfilePurposeType? = null,
    val stackLevel: Int? = null
)