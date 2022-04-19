package fr.simatix.cs.simulator.core20.model.signcertificate

import fr.simatix.cs.simulator.core20.model.common.enumeration.CertificateSigningUseEnumType

data class SignCertificateReq(
    val csr: String,
    val certificateType: CertificateSigningUseEnumType? = null
)