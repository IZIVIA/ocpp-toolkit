package fr.simatix.cs.simulator.api.model.getdisplaymessages

import fr.simatix.cs.simulator.api.model.Response
import fr.simatix.cs.simulator.api.model.common.StatusInfoType
import fr.simatix.cs.simulator.api.model.getdisplaymessages.enumeration.GetDisplayMessagesStatusEnumType

data class GetDisplayMessagesResp (
        val status: GetDisplayMessagesStatusEnumType,
        val statusInfo: StatusInfoType?=null
) : Response