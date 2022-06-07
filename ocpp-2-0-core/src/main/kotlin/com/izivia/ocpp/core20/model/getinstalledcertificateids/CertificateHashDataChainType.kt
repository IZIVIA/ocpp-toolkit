package com.izivia.ocpp.core20.model.getinstalledcertificateids

import com.izivia.ocpp.core20.model.common.CertificateHashDataType
import com.izivia.ocpp.core20.model.getinstalledcertificateids.enumeration.GetCertificateIdUseEnumType

data class CertificateHashDataChainType(
        val certificateType: GetCertificateIdUseEnumType,
        val certificateHashData: CertificateHashDataType,
        val childCertificateHashData: List<CertificateHashDataType>?=null
)
