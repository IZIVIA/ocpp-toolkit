package fr.simatix.cs.simulator.core20.model.sendlocallist

import fr.simatix.cs.simulator.core20.model.sendlocallist.enumeration.UpdateEnumType

data class SendLocalListReq(
    val versionNumber: Int,
    val updateType: UpdateEnumType,
    val localAuthorizationList: List<AuthorizationData>? = null
)