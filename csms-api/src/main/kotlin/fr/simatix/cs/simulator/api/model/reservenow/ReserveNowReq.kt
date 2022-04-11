package fr.simatix.cs.simulator.api.model.reservenow

import fr.simatix.cs.simulator.api.model.Request
import fr.simatix.cs.simulator.api.model.common.IdTokenType
import fr.simatix.cs.simulator.api.model.reservenow.enumeration.ConnectorEnumType
import kotlinx.datetime.Instant

data class ReserveNowReq(
    val id: Int,
    val expiryDateTime: Instant,
    val connectorType: ConnectorEnumType? = null,
    val evseId: Int? = null,
    val idToken: IdTokenType,
    val groupIdToken: IdTokenType
): Request