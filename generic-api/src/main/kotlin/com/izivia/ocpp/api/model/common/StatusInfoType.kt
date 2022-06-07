package com.izivia.ocpp.api.model.common

data class StatusInfoType(
    val reasonCode: String,
    val additionalInfo: String? = null
)