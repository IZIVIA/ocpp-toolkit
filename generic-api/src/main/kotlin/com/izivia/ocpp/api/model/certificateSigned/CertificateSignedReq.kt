package com.izivia.ocpp.api.model.certificateSigned

import com.izivia.ocpp.api.model.Request
import com.izivia.ocpp.api.model.common.enumeration.CertificateSigningUseEnumType

data class CertificateSignedReq(
    val certificateChain: String,
    val certificateType: CertificateSigningUseEnumType? = null,
) : Request