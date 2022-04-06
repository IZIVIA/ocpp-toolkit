package fr.simatix.cs.simulator.api.model.cancelreservation

import fr.simatix.cs.simulator.api.model.Response
import fr.simatix.cs.simulator.api.model.cancelreservation.enumeration.CancelReservationStatusEnumType
import fr.simatix.cs.simulator.api.model.common.StatusInfoType

data class CancelReservationResp(
    val status: CancelReservationStatusEnumType,
    val statusInfo: StatusInfoType? = null
): Response