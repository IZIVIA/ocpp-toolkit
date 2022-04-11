package fr.simatix.cs.simulator.core20.model.sendlocallist

import fr.simatix.cs.simulator.core20.model.common.IdTokenInfoType
import fr.simatix.cs.simulator.core20.model.common.IdTokenType

data class AuthorizationData(
    val idToken: IdTokenType,
    val idTokenInfo: IdTokenInfoType? = null
)