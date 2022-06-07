package com.izivia.ocpp.api.model.remotestart

import com.izivia.ocpp.api.model.Request
import com.izivia.ocpp.api.model.common.ChargingProfileType
import com.izivia.ocpp.api.model.common.IdTokenType

data class RequestStartTransactionReq(
    val remoteStartId: Int,
    val idToken: IdTokenType,
    val evseId: Int? = null,
    val chargingProfile: ChargingProfileType? = null,
    val groupIdToken: IdTokenType? = null
): Request