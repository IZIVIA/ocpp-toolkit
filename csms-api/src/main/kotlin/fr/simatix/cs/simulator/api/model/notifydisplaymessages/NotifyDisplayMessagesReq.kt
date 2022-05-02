package fr.simatix.cs.simulator.api.model.notifydisplaymessages

import fr.simatix.cs.simulator.api.model.Request
import fr.simatix.cs.simulator.api.model.common.MessageInfoType

data class NotifyDisplayMessagesReq(
    val requestId: Int,
    val tbc: Boolean = false,
    val messageInfo: List<MessageInfoType>? = null
): Request