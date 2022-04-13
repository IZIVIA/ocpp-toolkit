package fr.simatix.cs.simulator.core20.model.getcertificatestatus

import fr.simatix.cs.simulator.core20.model.common.OCSPRequestDataType

data class GetCertificateStatusReq(
    val ocspRequestData: OCSPRequestDataType
)