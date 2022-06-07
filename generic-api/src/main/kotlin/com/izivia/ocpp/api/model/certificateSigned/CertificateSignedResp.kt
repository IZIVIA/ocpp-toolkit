package com.izivia.ocpp.api.model.certificateSigned

import com.izivia.ocpp.api.model.Response
import com.izivia.ocpp.api.model.certificateSigned.enumeration.CertificateSignedStatusEnumType
import com.izivia.ocpp.api.model.common.StatusInfoType

data class CertificateSignedResp(
    val status: CertificateSignedStatusEnumType,
    val statusInfo: StatusInfoType? = null
): Response