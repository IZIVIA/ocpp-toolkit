package fr.simatix.cs.simulator.api.model.remotestart

import fr.simatix.cs.simulator.api.model.Request
import fr.simatix.cs.simulator.api.model.common.ChargingProfileType
import fr.simatix.cs.simulator.api.model.common.IdTokenType

data class RequestStartTransactionReq(
    val remoteStartId: Int,
    val idToken: IdTokenType,
    val evseId: Int? = null,
    val chargingProfile: ChargingProfileType? = null,
    val groupIdToken: IdTokenType? = null
): Request