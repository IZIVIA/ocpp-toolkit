package fr.simatix.cs.simulator.core20.model.remotestart

import fr.simatix.cs.simulator.core20.model.common.ChargingProfileType
import fr.simatix.cs.simulator.core20.model.common.IdTokenType

data class RequestStartTransactionReq(
    val remoteStartId: Int,
    val idToken: IdTokenType,
    val evseId: Int? = null,
    val chargingProfile: ChargingProfileType? = null,
    val groupIdToken: IdTokenType? = null
)