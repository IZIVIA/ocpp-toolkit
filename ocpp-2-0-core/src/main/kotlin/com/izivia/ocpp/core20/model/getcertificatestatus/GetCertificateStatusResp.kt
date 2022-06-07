package com.izivia.ocpp.core20.model.getcertificatestatus

import com.izivia.ocpp.core20.model.common.StatusInfoType
import com.izivia.ocpp.core20.model.getcertificatestatus.enumeration.GetCertificateStatusEnumType

data class GetCertificateStatusResp(
    val status: GetCertificateStatusEnumType,
    val ocspResult: String? = null,
    val statusInfo: StatusInfoType? = null
)