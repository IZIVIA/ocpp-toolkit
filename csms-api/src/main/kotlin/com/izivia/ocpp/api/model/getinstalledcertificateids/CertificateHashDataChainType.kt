package com.izivia.ocpp.api.model.getinstalledcertificateids

import com.izivia.ocpp.api.model.common.CertificateHashDataType
import com.izivia.ocpp.api.model.getinstalledcertificateids.enumeration.GetCertificateIdUseEnumType


data class CertificateHashDataChainType(
    val certificateType: GetCertificateIdUseEnumType,
    val certificateHashData: CertificateHashDataType,
    val childCertificateHashData: List<CertificateHashDataType>?=null
)
