package fr.simatix.cs.simulator.api.model.common

import fr.simatix.cs.simulator.api.model.common.enumeration.AuthorizationStatusEnumType
import kotlinx.datetime.Instant

data class IdTokenInfoType(
    val status: AuthorizationStatusEnumType,
    val cacheExpiryDateTime: Instant? = null,
    val chargingPriority: Int? = null,
    val language1: String? = null,
    val evseId: List<Int>? = null,
    val language2: String? = null,
    val groupIdToken: IdTokenType? = null,
    val personalMessage: MessageContentType? = null
)
