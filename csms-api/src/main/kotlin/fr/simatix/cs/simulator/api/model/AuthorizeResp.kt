package fr.simatix.cs.simulator.api.model

import fr.simatix.cs.simulator.api.model.enumeration.AuthorizeCertificateStatusEnumType

data class AuthorizeResp(
    val idTokenInfo: IdTokenInfoType,
    val certificateStatus: AuthorizeCertificateStatusEnumType? = null
)
