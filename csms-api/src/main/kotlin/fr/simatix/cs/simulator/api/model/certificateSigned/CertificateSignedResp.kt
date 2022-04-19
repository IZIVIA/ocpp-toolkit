package fr.simatix.cs.simulator.api.model.certificateSigned

import fr.simatix.cs.simulator.api.model.Response
import fr.simatix.cs.simulator.api.model.certificateSigned.enumeration.CertificateSignedStatusEnumType
import fr.simatix.cs.simulator.api.model.common.StatusInfoType

data class CertificateSignedResp(
    val status: CertificateSignedStatusEnumType,
    val statusInfo: StatusInfoType? = null
): Response