package com.izivia.ocpp.api.model.reservationstatusupdate

import com.izivia.ocpp.api.model.Request
import com.izivia.ocpp.api.model.reservationstatusupdate.enumeration.ReservationUpdateStatusEnumType

data class ReservationStatusUpdateReq(
    val reservationId: Int,
    val reservationUpdateStatus: ReservationUpdateStatusEnumType
): Request