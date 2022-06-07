package com.izivia.ocpp.api.model.getinstalledcertificateids

import com.izivia.ocpp.api.model.Response
import com.izivia.ocpp.api.model.common.StatusInfoType
import com.izivia.ocpp.api.model.getinstalledcertificateids.enumeration.GetInstalledCertificateStatusEnumType

data class GetInstalledCertificateIdsResp(
    val status: GetInstalledCertificateStatusEnumType,
    val certificateHashDataChain: List<CertificateHashDataChainType>?=null,
    val statusInfo : StatusInfoType?=null
) : Response
