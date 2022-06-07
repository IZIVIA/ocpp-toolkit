package com.izivia.ocpp.api.model.common

import com.izivia.ocpp.api.model.common.enumeration.MessageFormatEnumType

data class MessageContentType(
    val format: MessageFormatEnumType,
    val content: String,
    val language: String? = null
)
