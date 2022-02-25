package fr.simatix.cs.simulator.api.model.common

import fr.simatix.cs.simulator.api.model.common.enumeration.MessageFormatEnumType

data class MessageContentType(
    val format: MessageFormatEnumType,
    val content: String,
    val language: String? = null
)
