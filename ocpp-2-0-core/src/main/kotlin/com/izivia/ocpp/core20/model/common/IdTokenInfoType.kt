package com.izivia.ocpp.core20.model.common

import com.izivia.ocpp.core20.model.common.enumeration.AuthorizationStatusEnumType
import kotlinx.datetime.Instant

data class IdTokenInfoType(
    val status: AuthorizationStatusEnumType,
    val cacheExpiryDateTime: Instant? = null,
    val chargingPriority: Int? = null,
    val language1: String? = null,
    val evseId: List<Int>? = null,
    val language2: String? = null,
    val groupIdToken: IdTokenType? = null,
    val personalMessage: MessageContentType? = null
)
