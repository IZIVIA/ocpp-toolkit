package com.izivia.ocpp.core20.model.notifydisplaymessages

import com.izivia.ocpp.core20.model.common.MessageInfoType

data class NotifyDisplayMessagesReq(
    val requestId: Int,
    val tbc: Boolean = false,
    val messageInfo: List<MessageInfoType>? = null
)