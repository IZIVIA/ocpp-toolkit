package fr.simatix.cs.simulator.api.model.getcertificatestatus

import fr.simatix.cs.simulator.api.model.Request
import fr.simatix.cs.simulator.api.model.common.OCSPRequestDataType

data class GetCertificateStatusReq(
    val ocspRequestData: OCSPRequestDataType
): Request