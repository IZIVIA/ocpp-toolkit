package com.izivia.ocpp.core20.model.installcertificate

import com.izivia.ocpp.core20.model.installcertificate.enumeration.InstallCertificateUseEnumType

data class InstallCertificateReq(
        val certificateType : InstallCertificateUseEnumType,
        val certificate : String
)
