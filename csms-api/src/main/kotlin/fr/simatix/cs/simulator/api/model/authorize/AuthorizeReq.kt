package fr.simatix.cs.simulator.api.model.authorize

import fr.simatix.cs.simulator.api.model.Request
import fr.simatix.cs.simulator.api.model.common.IdTokenType
import fr.simatix.cs.simulator.api.model.common.OCSPRequestDataType

data class AuthorizeReq(
    val idToken: IdTokenType,
    val certificate: String? = null,
    val iso15118CertificateHashData: List<OCSPRequestDataType>? = null
): Request