package fr.simatix.cs.simulator.core16.model.sendlocallist

import fr.simatix.cs.simulator.core16.model.common.IdTagInfo

data class AuthorizationData(
    val idTag: String,
    val idTagInfo: IdTagInfo? = null
)