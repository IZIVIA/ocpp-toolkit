package fr.simatix.cs.simulator.api.model.getinstalledcertificateids

import fr.simatix.cs.simulator.api.model.Response
import fr.simatix.cs.simulator.api.model.common.StatusInfoType
import fr.simatix.cs.simulator.api.model.getinstalledcertificateids.enumeration.GetInstalledCertificateStatusEnumType

data class GetInstalledCertificateIdsResp(
        val status: GetInstalledCertificateStatusEnumType,
        val certificateHashDataChain: List<CertificateHashDataChainType>?=null,
        val statusInfo : StatusInfoType?=null
) : Response
