package fr.simatix.cs.simulator.api.model.getdisplaymessages

import fr.simatix.cs.simulator.api.model.Request
import fr.simatix.cs.simulator.api.model.common.enumeration.MessagePriorityEnumType
import fr.simatix.cs.simulator.api.model.common.enumeration.MessageStateEnumType

data class GetDisplayMessagesReq (
        val requestId : Int,
        val id : List<Int>?=null,
        val priority : MessagePriorityEnumType?=null,
        val state : MessageStateEnumType?=null

) : Request