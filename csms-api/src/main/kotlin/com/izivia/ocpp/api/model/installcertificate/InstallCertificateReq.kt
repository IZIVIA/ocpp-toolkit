package com.izivia.ocpp.api.model.installcertificate

import com.izivia.ocpp.api.model.Request
import com.izivia.ocpp.api.model.installcertificate.enumeration.InstallCertificateUseEnumType

data class InstallCertificateReq(
    val certificateType : InstallCertificateUseEnumType,
    val certificate : String
) : Request
