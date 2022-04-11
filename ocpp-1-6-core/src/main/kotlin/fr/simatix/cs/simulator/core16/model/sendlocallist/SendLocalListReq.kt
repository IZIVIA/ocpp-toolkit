package fr.simatix.cs.simulator.core16.model.sendlocallist

import fr.simatix.cs.simulator.core16.model.sendlocallist.enumeration.UpdateType

data class SendLocalListReq(
    val listVersion: Int,
    val updateType: UpdateType,
    val localAuthorizationList: List<AuthorizationData>? = null
)
