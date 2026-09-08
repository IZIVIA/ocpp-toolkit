package com.izivia.ocpp.core16.model.getinstalledcertificateids

import com.izivia.ocpp.core16.model.Response
import com.izivia.ocpp.core16.model.deletecertificate.CertificateHashDataType
import com.izivia.ocpp.core16.model.getinstalledcertificateids.enumeration.CertificateUseEnumType
import com.izivia.ocpp.core16.model.getinstalledcertificateids.enumeration.GetInstalledCertificateStatusEnumType

data class GetInstalledCertificateIdsResp(
    val status: GetInstalledCertificateStatusEnumType,
    val certificateHashData: List<CertificateHashDataType>?
) : Response
