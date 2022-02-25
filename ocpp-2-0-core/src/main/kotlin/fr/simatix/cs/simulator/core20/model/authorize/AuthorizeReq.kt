package fr.simatix.cs.simulator.core20.model.authorize

import fr.simatix.cs.simulator.core20.model.common.IdTokenType

data class AuthorizeReq(
    val idToken: IdTokenType,
    val certificate: String? = null,
    val iso15118CertificateHashData: List<OCSPRequestDataType>? = null
)
