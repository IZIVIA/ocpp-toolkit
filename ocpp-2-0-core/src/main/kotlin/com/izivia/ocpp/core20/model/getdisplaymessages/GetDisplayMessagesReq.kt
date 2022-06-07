package com.izivia.ocpp.core20.model.getdisplaymessages

import com.izivia.ocpp.core20.model.common.enumeration.MessagePriorityEnumType
import com.izivia.ocpp.core20.model.common.enumeration.MessageStateEnumType


data class GetDisplayMessagesReq (
        val requestId : Int,
        val id : List<Int>?=null,
        val priority : MessagePriorityEnumType?=null,
        val state : MessageStateEnumType?=null
)