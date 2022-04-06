package fr.simatix.cs.simulator.core16.model.cancelreservation

import fr.simatix.cs.simulator.core16.model.cancelreservation.enumeration.CancelReservationStatus

data class CancelReservationResp(
    val status: CancelReservationStatus
)