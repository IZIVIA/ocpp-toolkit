package com.izivia.ocpp.core20.model.signcertificate

import com.izivia.ocpp.core20.model.common.StatusInfoType
import com.izivia.ocpp.core20.model.common.enumeration.GenericStatusEnumType

data class SignCertificateResp(
    val status: GenericStatusEnumType,
    val statusInfo: StatusInfoType? = null
)