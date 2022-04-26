package fr.simatix.cs.simulator.api.model.getinstalledcertificateids

import fr.simatix.cs.simulator.api.model.common.CertificateHashDataType
import fr.simatix.cs.simulator.api.model.getinstalledcertificateids.enumeration.GetCertificateIdUseEnumType


data class CertificateHashDataChainType(
        val certificateType: GetCertificateIdUseEnumType,
        val certificateHashData: CertificateHashDataType,
        val childCertificateHashData: List<CertificateHashDataType>?=null
)
