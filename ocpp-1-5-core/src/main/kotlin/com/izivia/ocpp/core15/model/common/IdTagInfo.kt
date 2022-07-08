package com.izivia.ocpp.core15.model.common

import com.izivia.ocpp.core15.model.common.enumeration.AuthorisationStatus
import kotlinx.datetime.Instant

data class IdTagInfo(
    val expiryDate: Instant? = null,
    val parentIdTag: String? = null,
    val status: AuthorisationStatus
)
