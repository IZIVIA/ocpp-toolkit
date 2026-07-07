package com.izivia.ocpp.core16.model.certificatesigned

import com.izivia.ocpp.core16.model.Response
import com.izivia.ocpp.core16.model.certificatesigned.enumeration.CertificateSignedStatusEnumType

data class CertificateSignedResp(
    val status: CertificateSignedStatusEnumType
) : Response
