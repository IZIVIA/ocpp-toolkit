package com.izivia.ocpp.core20.model.installcertificate

import com.izivia.ocpp.core20.model.common.StatusInfoType
import com.izivia.ocpp.core20.model.installcertificate.enumeration.InstallCertificateStatusEnumType

data class InstallCertificateResp(
    val status : InstallCertificateStatusEnumType,
    val statusInfo : StatusInfoType?=null
)
