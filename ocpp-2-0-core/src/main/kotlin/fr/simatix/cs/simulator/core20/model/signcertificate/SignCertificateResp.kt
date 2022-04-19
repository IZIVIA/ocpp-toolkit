package fr.simatix.cs.simulator.core20.model.signcertificate

import fr.simatix.cs.simulator.core20.model.common.StatusInfoType
import fr.simatix.cs.simulator.core20.model.common.enumeration.GenericStatusEnumType

data class SignCertificateResp(
    val status: GenericStatusEnumType,
    val statusInfo: StatusInfoType? = null
)