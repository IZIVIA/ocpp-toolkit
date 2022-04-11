package fr.simatix.cs.simulator.core16.model.getcompositeschedule

import fr.simatix.cs.simulator.core16.model.common.enumeration.ChargingRateUnitType

data class GetCompositeScheduleReq(
    val connectorId: Int,
    val duration: Int,
    val chargingRateUnit: ChargingRateUnitType? = null
)
