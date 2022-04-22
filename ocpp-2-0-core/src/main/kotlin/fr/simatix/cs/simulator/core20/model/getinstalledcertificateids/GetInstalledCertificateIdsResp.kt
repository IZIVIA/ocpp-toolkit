package fr.simatix.cs.simulator.core20.model.getinstalledcertificateids

import fr.simatix.cs.simulator.core20.model.common.StatusInfoType


data class GetInstalledCertificateIdsResp(
        val status: GetInstalledCertificateStatusEnumType,
        val certificateHashDataChain: CertificateHashDataChainType?=null,
        val statusInfo : StatusInfoType?=null
)
