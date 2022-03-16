package fr.simatix.cs.simulator.core20.model.authorize

import fr.simatix.cs.simulator.core20.model.common.IdTokenInfoType
import fr.simatix.cs.simulator.core20.model.authorize.enumeration.AuthorizeCertificateStatusEnumType

data class AuthorizeResp(
    val idTokenInfo: IdTokenInfoType,
    val certificateStatus: AuthorizeCertificateStatusEnumType? = null
)
