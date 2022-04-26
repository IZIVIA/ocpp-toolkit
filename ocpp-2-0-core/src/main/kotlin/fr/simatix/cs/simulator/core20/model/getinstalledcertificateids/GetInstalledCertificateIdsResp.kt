package fr.simatix.cs.simulator.core20.model.getinstalledcertificateids

import fr.simatix.cs.simulator.core20.model.common.StatusInfoType
import fr.simatix.cs.simulator.core20.model.getinstalledcertificateids.enumeration.GetInstalledCertificateStatusEnumType


data class GetInstalledCertificateIdsResp(
        val status: GetInstalledCertificateStatusEnumType,
        val certificateHashDataChain: List<CertificateHashDataChainType>?=null,
        val statusInfo : StatusInfoType?=null
)
