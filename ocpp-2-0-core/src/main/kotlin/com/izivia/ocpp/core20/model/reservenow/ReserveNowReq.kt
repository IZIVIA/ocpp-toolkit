package com.izivia.ocpp.core20.model.reservenow

import com.izivia.ocpp.core20.model.common.IdTokenType
import com.izivia.ocpp.core20.model.reservenow.enumeration.ConnectorEnumType
import kotlinx.datetime.Instant

data class ReserveNowReq(
    val id: Int,
    val expiryDateTime: Instant,
    val connectorType: ConnectorEnumType? = null,
    val evseId: Int? = null,
    val idToken: IdTokenType,
    val groupIdToken: IdTokenType? = null
)