package fr.simatix.cs.simulator.api.model.setchargingprofile

import fr.simatix.cs.simulator.api.model.Request
import fr.simatix.cs.simulator.api.model.common.ChargingProfileType

data class SetChargingProfileReq(
    val evseId: Int,
    val chargingProfile: ChargingProfileType
): Request