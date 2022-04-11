package fr.simatix.cs.simulator.core20.model.setchargingprofile

import fr.simatix.cs.simulator.core20.model.common.ChargingProfileType

data class SetChargingProfileReq(
    val evseId: Int,
    val chargingProfile: ChargingProfileType
)