package fr.simatix.cs.simulator.core20.model.certificateSigned

import fr.simatix.cs.simulator.core20.model.common.enumeration.CertificateSigningUseEnumType

data class CertificateSignedReq(
    val certificateChain: String,
    val certificateType: CertificateSigningUseEnumType? = null,
)