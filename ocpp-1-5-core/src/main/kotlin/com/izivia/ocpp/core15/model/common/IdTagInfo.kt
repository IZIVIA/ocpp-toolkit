package com.izivia.ocpp.core15.model.common

import com.izivia.ocpp.core15.model.common.enumeration.AuthorizationStatus
import kotlinx.datetime.Instant

data class IdTagInfo(
    val expiryDate: Instant? = null,
    val parentIdTag: String? = null,
    val status: AuthorizationStatus
)
