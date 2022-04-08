package fr.simatix.cs.simulator.core20.model.clearchargingprofile

import fr.simatix.cs.simulator.core20.model.common.enumeration.ChargingProfilePurposeEnumType

data class ClearChargingProfileType(
    val evseId: Int? = null,
    val chargingProfilePurpose: ChargingProfilePurposeEnumType? = null,
    val stackLevel: Int? = null
)
