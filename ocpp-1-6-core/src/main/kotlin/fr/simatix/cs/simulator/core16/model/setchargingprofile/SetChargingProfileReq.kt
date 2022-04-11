package fr.simatix.cs.simulator.core16.model.setchargingprofile

import fr.simatix.cs.simulator.core16.model.common.ChargingProfile

data class SetChargingProfileReq(
    val connectorId: Int,
    val csChargingProfiles: ChargingProfile
)