package fr.simatix.cs.simulator.core20.model.authorize

import fr.simatix.cs.simulator.core20.model.common.IdTokenType
import fr.simatix.cs.simulator.core20.model.common.OCSPRequestDataType

data class AuthorizeReq(
    val idToken: IdTokenType,
    val certificate: String? = null,
    val iso15118CertificateHashData: List<OCSPRequestDataType>? = null
)
