package fr.simatix.cs.simulator.api.model.clearchargingprofile

import fr.simatix.cs.simulator.api.model.common.enumeration.ChargingProfilePurposeEnumType

data class ClearChargingProfileType(
    val evseId: Int? = null,
    val chargingProfilePurpose: ChargingProfilePurposeEnumType? = null,
    val stackLevel: Int? = null
)
