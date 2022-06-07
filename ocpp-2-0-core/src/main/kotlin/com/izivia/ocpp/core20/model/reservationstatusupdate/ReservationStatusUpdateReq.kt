package com.izivia.ocpp.core20.model.reservationstatusupdate

import com.izivia.ocpp.core20.model.reservationstatusupdate.enumeration.ReservationUpdateStatusEnumType

data class ReservationStatusUpdateReq(
    val reservationId: Int,
    val reservationUpdateStatus: ReservationUpdateStatusEnumType
)
