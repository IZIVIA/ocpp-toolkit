package fr.simatix.cs.simulator.api.model.reservationstatusupdate

import fr.simatix.cs.simulator.api.model.Request
import fr.simatix.cs.simulator.api.model.reservationstatusupdate.enumeration.ReservationUpdateStatusEnumType

data class ReservationStatusUpdateReq(
    val reservationId: Int,
    val reservationUpdateStatus: ReservationUpdateStatusEnumType
): Request