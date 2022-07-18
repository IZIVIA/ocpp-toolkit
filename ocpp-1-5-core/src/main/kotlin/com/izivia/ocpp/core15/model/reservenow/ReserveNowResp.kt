package com.izivia.ocpp.core15.model.reservenow

import com.izivia.ocpp.core15.model.reservenow.enumeration.ReservationStatus

data class ReserveNowResp(
    val status: ReservationStatus
)