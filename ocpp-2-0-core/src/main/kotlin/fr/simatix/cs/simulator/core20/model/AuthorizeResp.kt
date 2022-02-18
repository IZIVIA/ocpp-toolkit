package fr.simatix.cs.simulator.core20.model

import fr.simatix.cs.simulator.core20.model.enumeration.AuthorizeCertificateStatusEnumType

data class AuthorizeResp(
    val idTokenInfo: IdTokenInfoType,
    val certificateStatus: AuthorizeCertificateStatusEnumType? = null
)
