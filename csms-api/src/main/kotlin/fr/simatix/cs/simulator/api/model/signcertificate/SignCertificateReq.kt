package fr.simatix.cs.simulator.api.model.signcertificate

import fr.simatix.cs.simulator.api.model.Request
import fr.simatix.cs.simulator.api.model.common.enumeration.CertificateSigningUseEnumType

data class SignCertificateReq(
    val csr: String,
    val certificateType: CertificateSigningUseEnumType? = null
): Request