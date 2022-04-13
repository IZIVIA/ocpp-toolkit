package fr.simatix.cs.simulator.core20.model.notifydisplaymessages

import fr.simatix.cs.simulator.core20.model.common.enumeration.MessageFormatEnumType

data class MessageContentType(
    val format: MessageFormatEnumType,
    val language: String? = null,
    val content: String
)