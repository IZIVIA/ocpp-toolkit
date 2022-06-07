package com.izivia.ocpp.core20.model.authorize

import com.izivia.ocpp.core20.model.common.IdTokenInfoType
import com.izivia.ocpp.core20.model.authorize.enumeration.AuthorizeCertificateStatusEnumType

data class AuthorizeResp(
    val idTokenInfo: IdTokenInfoType,
    val certificateStatus: AuthorizeCertificateStatusEnumType? = null
)
