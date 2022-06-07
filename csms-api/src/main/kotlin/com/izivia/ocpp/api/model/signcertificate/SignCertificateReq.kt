package com.izivia.ocpp.api.model.signcertificate

import com.izivia.ocpp.api.model.Request
import com.izivia.ocpp.api.model.common.enumeration.CertificateSigningUseEnumType

data class SignCertificateReq(
    val csr: String,
    val certificateType: CertificateSigningUseEnumType? = null
): Request