package fr.simatix.cs.simulator.core20.model.getcertificatestatus

import fr.simatix.cs.simulator.core20.model.common.StatusInfoType
import fr.simatix.cs.simulator.core20.model.getcertificatestatus.enumeration.GetCertificateStatusEnumType

data class GetCertificateStatusResp(
    val status: GetCertificateStatusEnumType,
    val ocspResult: String? = null,
    val statusInfo: StatusInfoType? = null
)