package com.izivia.ocpp.core15.model.reservenow

import kotlinx.datetime.Instant

data class ReserveNowReq(
    val connectorId: Int,
    val expiryDate: Instant,
    val idTag: String,
    val parentIdTag: String? = null,
    val reservationId: Int
)
