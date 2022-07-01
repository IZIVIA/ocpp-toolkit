package com.izivia.ocpp.core16.model.common

import com.izivia.ocpp.core16.model.common.enumeration.AuthorizationStatus
import kotlinx.datetime.Instant

data class IdTagInfo(
    val status: AuthorizationStatus,
    val expiryDate: Instant? = null,
    val parentIdTag: String? = null
)