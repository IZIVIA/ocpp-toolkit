package fr.simatix.cs.simulator.core20.model.common

import fr.simatix.cs.simulator.core20.model.common.enumeration.MessageFormatEnumType

data class MessageContentType(
    val format: MessageFormatEnumType,
    val content: String,
    val language: String? = null
)
