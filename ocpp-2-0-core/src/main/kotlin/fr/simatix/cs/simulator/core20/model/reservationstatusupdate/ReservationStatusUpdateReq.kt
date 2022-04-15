package fr.simatix.cs.simulator.core20.model.reservationstatusupdate

import fr.simatix.cs.simulator.core20.model.reservationstatusupdate.enumeration.ReservationUpdateStatusEnumType

data class ReservationStatusUpdateReq(
    val reservationId: Int,
    val reservationUpdateStatus: ReservationUpdateStatusEnumType
)
