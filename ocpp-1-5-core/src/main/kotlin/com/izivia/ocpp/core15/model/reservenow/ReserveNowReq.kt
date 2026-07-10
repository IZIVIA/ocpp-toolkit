package com.izivia.ocpp.core15.model.reservenow

import com.izivia.ocpp.core15.model.Request
import kotlin.time.Instant

data class ReserveNowReq(
    val connectorId: Int,
    val expiryDate: Instant,
    val idTag: String,
    val parentIdTag: String? = null,
    val reservationId: Int
): Request
