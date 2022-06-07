package com.izivia.ocpp.api.model.authorize

import com.izivia.ocpp.api.model.Request
import com.izivia.ocpp.api.model.common.IdTokenType
import com.izivia.ocpp.api.model.common.OCSPRequestDataType

data class AuthorizeReq(
    val idToken: IdTokenType,
    val certificate: String? = null,
    val iso15118CertificateHashData: List<OCSPRequestDataType>? = null
): Request