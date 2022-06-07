package com.izivia.ocpp.api.model.getcertificatestatus

import com.izivia.ocpp.api.model.Response
import com.izivia.ocpp.api.model.common.StatusInfoType
import com.izivia.ocpp.api.model.getcertificatestatus.enumeration.GetCertificateStatusEnumType

data class GetCertificateStatusResp(
    val status: GetCertificateStatusEnumType,
    val ocspResult: String? = null,
    val statusInfo: StatusInfoType? = null
): Response
