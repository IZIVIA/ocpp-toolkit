package fr.simatix.cs.simulator.core20.model.sendlocallist

import fr.simatix.cs.simulator.core20.model.common.StatusInfoType
import fr.simatix.cs.simulator.core20.model.sendlocallist.enumeration.SendLocalListStatusEnumType

data class SendLocalListResp(
    val status: SendLocalListStatusEnumType,
    val statusInfo: StatusInfoType? = null
)