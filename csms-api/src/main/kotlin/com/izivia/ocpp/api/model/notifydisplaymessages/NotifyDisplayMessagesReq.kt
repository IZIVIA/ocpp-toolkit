package com.izivia.ocpp.api.model.notifydisplaymessages

import com.izivia.ocpp.api.model.Request
import com.izivia.ocpp.api.model.common.MessageInfoType

data class NotifyDisplayMessagesReq(
    val requestId: Int,
    val tbc: Boolean = false,
    val messageInfo: List<MessageInfoType>? = null
): Request