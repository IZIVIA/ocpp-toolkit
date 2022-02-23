package fr.simatix.cs.simulator.api.model

import fr.simatix.cs.simulator.api.model.enumeration.MessageFormatEnumType

data class MessageContentType(
    val format: MessageFormatEnumType,
    val content: String,
    val language: String? = null
)
