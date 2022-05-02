package fr.simatix.cs.simulator.core20.model.getdisplaymessages

import fr.simatix.cs.simulator.core20.model.common.StatusInfoType
import fr.simatix.cs.simulator.core20.model.getdisplaymessages.enumeration.GetDisplayMessagesStatusEnumType

data class GetDisplayMessagesResp (
        val status: GetDisplayMessagesStatusEnumType,
        val statusInfo: StatusInfoType?=null
)