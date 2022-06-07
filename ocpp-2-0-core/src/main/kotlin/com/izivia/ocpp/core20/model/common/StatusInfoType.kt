package com.izivia.ocpp.core20.model.common

data class StatusInfoType(
    val reasonCode: String,
    val additionalInfo: String? = null
)