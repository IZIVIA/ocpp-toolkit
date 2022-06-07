package com.izivia.ocpp.core20.model.signcertificate

import com.izivia.ocpp.core20.model.common.enumeration.CertificateSigningUseEnumType

data class SignCertificateReq(
    val csr: String,
    val certificateType: CertificateSigningUseEnumType? = null
)