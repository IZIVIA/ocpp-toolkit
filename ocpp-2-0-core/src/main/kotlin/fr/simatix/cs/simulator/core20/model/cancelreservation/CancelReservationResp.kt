package fr.simatix.cs.simulator.core20.model.cancelreservation

import fr.simatix.cs.simulator.core20.model.cancelreservation.enumeration.CancelReservationStatusEnumType
import fr.simatix.cs.simulator.core20.model.common.StatusInfoType

data class CancelReservationResp(
    val status: CancelReservationStatusEnumType,
    val statusInfo: StatusInfoType? = null
)