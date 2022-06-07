package com.izivia.ocpp.core20.model.authorize

import com.izivia.ocpp.core20.model.common.IdTokenType
import com.izivia.ocpp.core20.model.common.OCSPRequestDataType

data class AuthorizeReq(
    val idToken: IdTokenType,
    val certificate: String? = null,
    val iso15118CertificateHashData: List<OCSPRequestDataType>? = null
)
