package com.izivia.ocpp.api.model.sendlocallist

import com.izivia.ocpp.api.model.common.IdTokenInfoType
import com.izivia.ocpp.api.model.common.IdTokenType

data class AuthorizationData(
    val idToken: IdTokenType,
    val idTokenInfo: IdTokenInfoType? = null
)