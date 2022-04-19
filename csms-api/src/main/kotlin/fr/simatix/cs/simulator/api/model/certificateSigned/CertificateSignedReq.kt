package fr.simatix.cs.simulator.api.model.certificateSigned

import fr.simatix.cs.simulator.api.model.Request
import fr.simatix.cs.simulator.api.model.common.enumeration.CertificateSigningUseEnumType

data class CertificateSignedReq(
    val certificateChain: String,
    val certificateType: CertificateSigningUseEnumType? = null,
) : Request