package fr.simatix.cs.simulator.api.model.signcertificate

import fr.simatix.cs.simulator.api.model.Response
import fr.simatix.cs.simulator.api.model.common.StatusInfoType
import fr.simatix.cs.simulator.api.model.common.enumeration.GenericStatusEnumType

data class SignCertificateResp(
    val status: GenericStatusEnumType,
    val statusInfo: StatusInfoType? = null
): Response