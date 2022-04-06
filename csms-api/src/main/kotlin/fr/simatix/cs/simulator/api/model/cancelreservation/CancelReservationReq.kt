package fr.simatix.cs.simulator.api.model.cancelreservation

import fr.simatix.cs.simulator.api.model.Request

data class CancelReservationReq(
    val reservationId: Int
): Request