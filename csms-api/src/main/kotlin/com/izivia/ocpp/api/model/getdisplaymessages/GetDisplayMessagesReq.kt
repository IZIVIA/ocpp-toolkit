package com.izivia.ocpp.api.model.getdisplaymessages

import com.izivia.ocpp.api.model.Request
import com.izivia.ocpp.api.model.common.enumeration.MessagePriorityEnumType
import com.izivia.ocpp.api.model.common.enumeration.MessageStateEnumType

data class GetDisplayMessagesReq (
    val requestId : Int,
    val id : List<Int>?=null,
    val priority : MessagePriorityEnumType?=null,
    val state : MessageStateEnumType?=null

) : Request