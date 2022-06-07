package com.izivia.ocpp.core20.model.common

import com.izivia.ocpp.core20.model.common.enumeration.MessageFormatEnumType

data class MessageContentType(
    val format: MessageFormatEnumType,
    val content: String,
    val language: String? = null
)
