package com.izivia.ocpp.core20.model.certificateSigned

import com.izivia.ocpp.core20.model.certificateSigned.enumeration.CertificateSignedStatusEnumType
import com.izivia.ocpp.core20.model.common.StatusInfoType

data class CertificateSignedResp(
    val status: CertificateSignedStatusEnumType,
    val statusInfo: StatusInfoType? = null
)