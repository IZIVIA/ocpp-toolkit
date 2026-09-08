package com.izivia.ocpp.core12.model.common

import com.izivia.ocpp.core12.model.common.enumeration.AuthorizationStatus
import kotlin.time.Instant

data class IdTagInfo(
    val expiryDate: Instant? = null,
    val parentIdTag: String? = null,
    val status: AuthorizationStatus
)
