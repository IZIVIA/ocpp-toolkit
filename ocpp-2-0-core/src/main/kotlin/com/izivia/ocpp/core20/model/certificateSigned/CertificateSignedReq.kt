package com.izivia.ocpp.core20.model.certificateSigned

import com.izivia.ocpp.core20.model.common.enumeration.CertificateSigningUseEnumType

data class CertificateSignedReq(
    val certificateChain: String,
    val certificateType: CertificateSigningUseEnumType? = null,
)