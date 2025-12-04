package com.izivia.ocpp.core15.model.cancelreservation

import com.izivia.ocpp.core15.model.Request

data class CancelReservationReq(
    val reservationId: Int
): Request
