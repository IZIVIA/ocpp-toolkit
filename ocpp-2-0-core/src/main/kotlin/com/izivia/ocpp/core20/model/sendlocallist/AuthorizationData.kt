package com.izivia.ocpp.core20.model.sendlocallist

import com.izivia.ocpp.core20.model.common.IdTokenInfoType
import com.izivia.ocpp.core20.model.common.IdTokenType

data class AuthorizationData(
    val idToken: IdTokenType,
    val idTokenInfo: IdTokenInfoType? = null
)