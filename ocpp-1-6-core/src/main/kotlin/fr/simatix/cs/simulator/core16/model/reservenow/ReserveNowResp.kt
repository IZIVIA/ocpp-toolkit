package fr.simatix.cs.simulator.core16.model.reservenow

import fr.simatix.cs.simulator.core16.model.reservenow.enumeration.ReservationStatus

data class ReserveNowResp(
    val status: ReservationStatus
)