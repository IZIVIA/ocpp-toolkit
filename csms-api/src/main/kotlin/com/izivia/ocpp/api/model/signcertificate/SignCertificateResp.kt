package com.izivia.ocpp.api.model.signcertificate

import com.izivia.ocpp.api.model.Response
import com.izivia.ocpp.api.model.common.StatusInfoType
import com.izivia.ocpp.api.model.common.enumeration.GenericStatusEnumType

data class SignCertificateResp(
    val status: GenericStatusEnumType,
    val statusInfo: StatusInfoType? = null
): Response