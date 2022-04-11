package fr.simatix.cs.simulator.api.model.sendlocallist

import fr.simatix.cs.simulator.api.model.common.IdTokenInfoType
import fr.simatix.cs.simulator.api.model.common.IdTokenType

data class AuthorizationData(
    val idToken: IdTokenType,
    val idTokenInfo: IdTokenInfoType? = null
)