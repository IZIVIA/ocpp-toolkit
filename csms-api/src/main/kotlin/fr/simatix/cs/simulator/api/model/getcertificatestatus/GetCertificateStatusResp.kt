package fr.simatix.cs.simulator.api.model.getcertificatestatus

import fr.simatix.cs.simulator.api.model.Response
import fr.simatix.cs.simulator.api.model.common.StatusInfoType
import fr.simatix.cs.simulator.api.model.getcertificatestatus.enumeration.GetCertificateStatusEnumType

data class GetCertificateStatusResp(
    val status: GetCertificateStatusEnumType,
    val ocspResult: String? = null,
    val statusInfo: StatusInfoType? = null
): Response
