package com.izivia.ocpp.core16.model.reservenow

import com.izivia.ocpp.core16.model.reservenow.enumeration.ReservationStatus

data class ReserveNowResp(
    val status: ReservationStatus
)