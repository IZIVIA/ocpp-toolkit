package com.izivia.ocpp.api.model.authorize

import com.izivia.ocpp.api.model.Response
import com.izivia.ocpp.api.model.common.IdTokenInfoType
import com.izivia.ocpp.api.model.authorize.enumeration.AuthorizeCertificateStatusEnumType

data class AuthorizeResp(
    val idTokenInfo: IdTokenInfoType,
    val certificateStatus: AuthorizeCertificateStatusEnumType? = null
): Response
