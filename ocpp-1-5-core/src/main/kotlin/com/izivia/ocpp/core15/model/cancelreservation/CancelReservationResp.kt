package com.izivia.ocpp.core15.model.cancelreservation

import com.izivia.ocpp.core15.model.cancelreservation.enumeration.CancelReservationStatus

data class CancelReservationResp(
    val status: CancelReservationStatus
)