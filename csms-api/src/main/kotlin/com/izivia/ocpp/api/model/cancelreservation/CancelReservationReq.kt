package com.izivia.ocpp.api.model.cancelreservation

import com.izivia.ocpp.api.model.Request

data class CancelReservationReq(
    val reservationId: Int
): Request