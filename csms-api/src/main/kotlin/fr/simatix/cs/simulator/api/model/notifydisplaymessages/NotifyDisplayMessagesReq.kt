package fr.simatix.cs.simulator.api.model.notifydisplaymessages

import fr.simatix.cs.simulator.api.model.Request

data class NotifyDisplayMessagesReq(
    val requestId: Int,
    val tbc: Boolean = false,
    val messageInfo: MessageInfoType? = null
): Request