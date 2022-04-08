package fr.simatix.cs.simulator.api.model.clearchargingprofile

import fr.simatix.cs.simulator.api.model.Request

data class ClearChargingProfileReq(
    val chargingProfileId: Int? = null,
    val chargingProfileCriteria: ClearChargingProfileType? = null
): Request
