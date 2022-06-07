package com.izivia.ocpp.core20.model.getinstalledcertificateids

import com.izivia.ocpp.core20.model.common.StatusInfoType
import com.izivia.ocpp.core20.model.getinstalledcertificateids.enumeration.GetInstalledCertificateStatusEnumType


data class GetInstalledCertificateIdsResp(
        val status: GetInstalledCertificateStatusEnumType,
        val certificateHashDataChain: List<CertificateHashDataChainType>?=null,
        val statusInfo : StatusInfoType?=null
)
