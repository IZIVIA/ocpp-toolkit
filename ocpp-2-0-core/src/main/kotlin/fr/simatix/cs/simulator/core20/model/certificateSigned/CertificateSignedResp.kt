package fr.simatix.cs.simulator.core20.model.certificateSigned

import fr.simatix.cs.simulator.core20.model.certificateSigned.enumeration.CertificateSignedStatusEnumType
import fr.simatix.cs.simulator.core20.model.common.StatusInfoType

data class CertificateSignedResp(
    val status: CertificateSignedStatusEnumType,
    val statusInfo: StatusInfoType? = null
)