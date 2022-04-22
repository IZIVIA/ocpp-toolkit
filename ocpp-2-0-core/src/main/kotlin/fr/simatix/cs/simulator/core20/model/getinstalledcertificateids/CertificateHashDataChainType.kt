package fr.simatix.cs.simulator.core20.model.getinstalledcertificateids

import fr.simatix.cs.simulator.core20.model.getinstalledcertificateids.enumeration.GetCertificateIdUseEnumType

data class CertificateHashDataChainType(
        val certificateType: GetCertificateIdUseEnumType,
        val certificateHashData: CertificateHashDataType,
        val childCertificateHashData: CertificateHashDataType
)
