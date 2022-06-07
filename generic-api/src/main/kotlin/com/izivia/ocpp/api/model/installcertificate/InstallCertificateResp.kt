package com.izivia.ocpp.api.model.installcertificate

import com.izivia.ocpp.api.model.Response
import com.izivia.ocpp.api.model.common.StatusInfoType
import com.izivia.ocpp.api.model.installcertificate.enumeration.InstallCertificateStatusEnumType

data class InstallCertificateResp(
    val status : InstallCertificateStatusEnumType,
    val statusInfo : StatusInfoType?=null
) : Response
