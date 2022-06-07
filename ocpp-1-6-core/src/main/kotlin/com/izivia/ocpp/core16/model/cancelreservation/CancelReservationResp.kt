package com.izivia.ocpp.core16.model.cancelreservation

import com.izivia.ocpp.core16.model.cancelreservation.enumeration.CancelReservationStatus

data class CancelReservationResp(
    val status: CancelReservationStatus
)