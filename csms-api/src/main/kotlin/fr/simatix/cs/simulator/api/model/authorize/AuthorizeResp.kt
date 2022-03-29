package fr.simatix.cs.simulator.api.model.authorize

import fr.simatix.cs.simulator.api.model.Response
import fr.simatix.cs.simulator.api.model.common.IdTokenInfoType
import fr.simatix.cs.simulator.api.model.authorize.enumeration.AuthorizeCertificateStatusEnumType

data class AuthorizeResp(
    val idTokenInfo: IdTokenInfoType,
    val certificateStatus: AuthorizeCertificateStatusEnumType? = null
): Response
