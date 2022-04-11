package fr.simatix.cs.simulator.api.model.sendlocallist

import fr.simatix.cs.simulator.api.model.Request
import fr.simatix.cs.simulator.api.model.sendlocallist.enumeration.UpdateEnumType

data class SendLocalListReq(
    val versionNumber: Int,
    val updateType: UpdateEnumType,
    val localAuthorizationList: List<AuthorizationData>? = null
): Request