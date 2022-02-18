package fr.simatix.cs.simulator.core20.model

import fr.simatix.cs.simulator.core20.model.enumeration.MessageFormatEnumType

data class MessageContentType(
    val format: MessageFormatEnumType,
    val content: String,
    val language: String? = null
)
