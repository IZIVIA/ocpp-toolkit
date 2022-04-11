package fr.simatix.cs.simulator.api.model.sendlocallist

import fr.simatix.cs.simulator.api.model.Response
import fr.simatix.cs.simulator.api.model.common.StatusInfoType
import fr.simatix.cs.simulator.api.model.sendlocallist.enumeration.SendLocalListStatusEnumType

data class SendLocalListResp(
    val status: SendLocalListStatusEnumType,
    val statusInfo: StatusInfoType? = null
): Response
