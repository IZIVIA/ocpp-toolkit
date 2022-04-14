package fr.simatix.cs.simulator.core20.model.notifydisplaymessages

data class NotifyDisplayMessagesReq(
    val requestId: Int,
    val tbc: Boolean = false,
    val messageInfo: List<MessageInfoType>? = null
)