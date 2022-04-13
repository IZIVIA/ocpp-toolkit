package fr.simatix.cs.simulator.api.model.notifydisplaymessages

import fr.simatix.cs.simulator.api.model.common.enumeration.MessageFormatEnumType

data class MessageContentType(
    val format: MessageFormatEnumType,
    val language: String? = null,
    val content: String
)